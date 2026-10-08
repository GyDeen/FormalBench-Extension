"""Native execution and sanitizer replay; no input generation."""
import json
import os
import re
import subprocess
import time
from verification.specification_evaluation.manifest import REPO
from verification.specification_evaluation.workflow import write_json
from .c_contracts import CONTRACTS, parameters
HELPERS = r'''
#include <stdio.h>
#include <stdlib.h>
#include <stdint.h>
#include <inttypes.h>
#include <errno.h>
#include "RUNTIME_HEADER"
static int32_t replay_integer(char**v,int*at){return (int32_t)strtoll(v[(*at)++],NULL,10);}
static JIntArray replay_array(char**v,int*at){int32_t n=replay_integer(v,at);if(n<0)return NULL;JIntArray a=jarray_new(n);for(int32_t i=0;i<n;i++)jarray_set(a,i,replay_integer(v,at));return a;}
static JIntArray2 replay_matrix(char**v,int*at){int32_t n=replay_integer(v,at);if(n<0)return NULL;JIntArray2 a=jarray2_new_rows(n);for(int32_t i=0;i<n;i++)jarray2_set(a,i,replay_array(v,at));return a;}
static void replay_print_array(JIntArray a){if(a==NULL){printf("null");return;}int32_t n=jarray_length(a);printf("[");for(int32_t i=0;i<n;i++){if(i)printf(",");printf("%"PRId32,jarray_get(a,i));}printf("]");}
static void replay_print_matrix(JIntArray2 a){if(a==NULL){printf("null");return;}int32_t n=jarray2_length(a);printf("[");for(int32_t i=0;i<n;i++){if(i)printf(",");replay_print_array(jarray2_get(a,i));}printf("]");}
static void replay_print_double_array(JDoubleArray a){if(a==NULL){printf("null");return;}int32_t n=jdouble_array_length(a);printf("[");for(int32_t i=0;i<n;i++){if(i)printf(",");printf("%.17g",jdouble_array_get(a,i));}printf("]");}
'''

def native_harness(program, source):
    runtime_header = str(REPO/'runtime/java_arrays/java_arrays.h')
    source = source.replace('#include "java_arrays.h"', '#include "'+runtime_header+'"')
    code = HELPERS.replace('RUNTIME_HEADER',runtime_header)+source+'\nint main(int argc,char**argv){(void)argc;int at=1;\n'
    for kind,name in parameters(program):
        parser = 'replay_integer' if kind == 'int32_t' else 'replay_matrix' if kind == 'JIntArray2' else 'replay_array'
        code += f'{kind} {name}={parser}(argv,&at);\n'
    kind = CONTRACTS[program]['return_type']
    args = ','.join(name for _,name in parameters(program))
    code += f'errno=0;{kind} result={CONTRACTS[program]["entry"]}({args});int result_errno=errno;\n'
    code += 'printf("\\n@@REPLAY {\\"result\\":");'
    printers = {'int32_t':'printf("%"PRId32,result);','JIntArray':'replay_print_array(result);','JIntArray2':'replay_print_matrix(result);','JDoubleArray':'replay_print_double_array(result);'}
    code += printers[kind]+'printf(",\\"state\\":{");'
    arrparams = [(k,n) for k,n in parameters(program) if k.startswith('J')]
    for index,(k,n) in enumerate(arrparams):
        code += f'printf("{"," if index else ""}\\"{n}\\":");'+('replay_print_matrix' if k=='JIntArray2' else 'replay_print_array')+f'({n});'
    code += 'printf("},\\"aliases\\":{");'
    for index,(_,n) in enumerate(arrparams):
        flag = f'(void*)result==(void*){n}' if kind.startswith('J') else '0'
        code += f'printf("{"," if index else ""}\\"{n}\\":%s",({flag})?"true":"false");'
    if kind == 'JIntArray2':
        code += 'int separate=1;if(result!=NULL){int32_t n=jarray2_length(result);for(int32_t i=0;i<n;i++)for(int32_t j=i+1;j<n;j++)if(jarray2_get(result,i)==jarray2_get(result,j))separate=0;} '
    else: code += 'int separate=1;'
    code += 'printf("},\\"rows_separate\\":%s,\\"errno\\":%d,\\"EDOM\\":%d}\\n",separate?"true":"false",result_errno,EDOM);return 0;}\n'
    return code

def native_args(program, inputs):
    args=[]
    def array(a): return [-1] if a is None else [len(a),*a]
    for kind,name in parameters(program):
        value=inputs[name]
        if kind=='int32_t': args.append(value)
        elif kind=='JIntArray': args.extend(array(value))
        elif value is None: args.append(-1)
        else:
            args.append(len(value))
            for row in value: args.extend(array(row))
    return list(map(str,args))

def execute(binary, program, inputs, timeout=0.5):
    started=time.monotonic()
    try:
        response=subprocess.run([str(binary),*native_args(program,inputs)],capture_output=True,text=True,timeout=timeout,
            env={**os.environ,'ASAN_OPTIONS':'detect_leaks=0:halt_on_error=1','UBSAN_OPTIONS':'halt_on_error=1:print_stacktrace=1'})
    except subprocess.TimeoutExpired:
        return {'status':'timeout','elapsed_seconds':time.monotonic()-started}
    result={'exit_code':response.returncode,'stdout':response.stdout,'stderr':response.stderr,'elapsed_seconds':time.monotonic()-started}
    if response.returncode in (71,72,73) or 'JAVA_ARITHMETIC_ERROR:' in response.stderr or 'runtime error:' in response.stderr or 'ERROR: AddressSanitizer:' in response.stderr:
        result['status']='runtime_safety'
    elif response.returncode==0:
        values=re.findall(r'(?m)^@@REPLAY (.+)$',response.stdout)
        try:
            result['observation']=json.loads(values[-1])
            result['status']='returned' if not response.stderr.strip() else 'diagnostic'
        except (IndexError,ValueError): result['status']='unavailable_output'
    else: result['status']='abnormal_exit'
    return result

def compile_native(directory, program, audit=False):
    suffix='audit' if audit else 'search'
    harness=directory/'native_replay.c'; binary=directory/f'native_replay_{suffix}'
    command=['gcc','-std=c11','-O1' if audit else '-O0','-g','-Wall','-Werror=return-type']
    if audit: command+=['-Werror=uninitialized','-Werror=maybe-uninitialized']
    command+=['-fsanitize=address,undefined' if audit else '-fsanitize=undefined','-fno-sanitize-recover=all',str(harness),
        str(REPO/'runtime/java_arrays/java_arrays.c'),'-lm','-o',str(binary)]
    response=subprocess.run(command,capture_output=True,text=True,timeout=60)
    record={'command':command,'exit_code':response.returncode,'stdout':response.stdout,'stderr':response.stderr}
    write_json(directory/f'native_{suffix}_compile.json',record)
    return binary if response.returncode==0 else None
