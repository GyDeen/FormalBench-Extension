"""Concrete checks of frozen C contracts, independent of the C implementation.

These checks cover return values, input frames, array identity and selected
allocation properties. Passing finite trials never proves the entire contract.
"""
from functools import lru_cache
import itertools
import json
import math
from pathlib import Path
import random

from .c_counterexamples import int32, oracle as six_oracle

CONTRACTS = json.loads(Path(__file__).with_name('c_replay_contracts.json').read_text(encoding='utf-8-sig'))
MAXINT = 2**31 - 1
ARRAY_MUTATORS = {'CombSort', 'RadixSort'}


def parameters(program):
    return [tuple(p.split()) for p in CONTRACTS[program]['parameters']]


def admissible(program, x):
    """Frozen requires clauses; resource limits belong in search, not here."""
    if set(x) != {name for _, name in parameters(program)}:
        return False
    def scalar(v):
        return type(v) is int and -2**31 <= v <= MAXINT
    def array(v):
        return isinstance(v, list) and len(v) <= MAXINT and all(scalar(i) for i in v)
    def matrix(v):
        return isinstance(v, list) and len(v) <= MAXINT and all(r is None or array(r) for r in v)
    for kind, name in parameters(program):
        v = x[name]
        if kind == 'int32_t' and not scalar(v):
            return False
        if kind == 'JIntArray' and v is not None and not array(v):
            return False
        if kind == 'JIntArray2' and v is not None and not matrix(v):
            return False
    if program == 'MoveFirst':
        return True
    if program == 'FindPeak':
        return x['n'] >= 0 and (x['n'] <= 1 or array(x['arr']) and x['n'] <= len(x['arr']))
    if program in {'MaxSubArraySum', 'SumOfSubarrayProd'}:
        n = x.get('size', x.get('n'))
        a = x.get('a', x.get('arr'))
        return n <= 0 or array(a) and n <= len(a)
    if program == 'SumRangeList':
        return x['m'] > x['n'] or array(x['nums']) and 0 <= x['m'] and x['n'] < len(x['nums'])
    if program == 'MinCoins':
        return x['v'] <= 0 or x['m'] <= 0 or array(x['coins']) and x['m'] <= len(x['coins']) and all(v > 0 for v in x['coins'][:x['m']])
    if program == 'MinJumps':
        return x['n'] >= 1 and (x['n'] == 1 or array(x['arr']) and x['n'] - 1 <= len(x['arr']))
    if program == 'MinCost':
        a, m, n = x['cost'], x['m'], x['n']
        return matrix(a) and 0 <= m < len(a) and n >= 0 and all(array(r) and n < len(r) for r in a[:m+1])
    for kind, name in parameters(program):
        if kind == 'JIntArray' and not array(x[name]):
            return False
        if kind == 'JIntArray2' and not matrix(x[name]):
            return False
    if program == 'CountList':
        return all(array(r) for r in x['inputArray'])
    if program == 'MaxDifference':
        return all(array(r) and len(r) >= 2 for r in x['testArray'])
    if program in {'CountingSort', 'RadixSort'}:
        a = next(v for v in x.values() if isinstance(v, list))
        return (program != 'RadixSort' or bool(a)) and (not a or max(a) - min(a) < MAXINT)
    if program in {'MaxProduct', 'MaxSumOfThreeConsecutive'}:
        return 1 <= x['n'] <= len(x['arr'])
    if program == 'MaxSumSubseq':
        return len(x['a']) < MAXINT
    if program == 'CountWays':
        return 1 <= x['n'] < MAXINT
    if program in {'Fibonacci', 'NewmanPrime'}:
        return x['n'] >= 0
    if program == 'DealnnoyNum':
        return x['n'] == 0 or x['m'] == 0 or x['n'] > 0 and x['m'] > 0
    if program == 'MaximumSegments':
        return 0 <= x['n'] < MAXINT and min(x['a'], x['b'], x['c']) > 0
    if program == 'ParabolaVertex':
        return x['a'] != 0
    if program == 'SumOfPrimes':
        return -1 <= x['n'] < 46349
    if program == 'LeftInsertion':
        return expected(program, x) >= 0
    return True


def searchable(program, x):
    """Limit work on valid inputs so large models cannot exhaust replay."""
    for kind,name in parameters(program):
        if kind.startswith('J') and x[name] is not None:
            a=x[name]
            if len(a)>32 or kind=='JIntArray2' and any(row is not None and len(row)>32 for row in a):
                return False
    if program in {'CountUnsetBits', 'CountOddSquares', 'MaxVolume', 'CountWays', 'MaximumSegments'}:
        return max(x.values()) <= 64 and (program != 'CountOddSquares' or x['m']-x['n'] <= 128)
    if program in {'Fibonacci', 'NewmanPrime', 'DealnnoyNum'}:
        return max(x.values()) <= 9
    if program == 'SumOfPrimes':
        return x['n'] <= 128
    if program == 'MinCoins':
        return x['v'] <= 16
    if program in {'CountingSort', 'RadixSort'}:
        a = next(iter(x.values()))
        return not a or max(a) - min(a) <= 256
    return True


def expected(p, x):
    """Executable frozen logic definitions, with explicit Java int wrapping."""
    if p in {'MaxOfTwo','OddBitSetNumber','SumNums','TestThreeEqual','CountList','MaxSubArraySum'}:
        return six_oracle(p, x)
    if p == 'CountIntgralPoints':
        return int32((x['y2']-x['y1']-1)*(x['x2']-x['x1']-1))
    if p == 'DiameterCircle': return int32(2*x['r'])
    if p == 'SquarePerimeter': return int32(4*x['a'])
    if p == 'VolumeCube': return int32(x['l']**3)
    if p == 'FindRectNum': return int32(x['n']*(x['n']+1))
    if p == 'HexagonalNum': return int32(x['n']*(2*x['n']-1))
    if p == 'NoOfCubes': return int32((x['n']-x['k']+1)**3)
    if p == 'DogAge': return int32((x['hAge']-2 if x['hAge'] >= 0 else x['hAge']+2)*4+21)
    if p == 'TriangleArea': return -1 if x['r'] < 0 else int32(x['r']**2)
    if p == 'ParallelogramPerimeter': return 0 if min(x.values()) <= 0 else int32(2*x['b']*x['h'])
    if p == 'FindPoints':
        l1,r1,l2,r2 = (x[n] for n in ('l1','r1','l2','r2'))
        return [min(l1,r1),max(l2,r2)] if l1 < l2 and r1 < r2 else [min(l2,r2),max(l1,r1)] if l1 > l2 and r1 > r2 else [l1,r1]
    if p == 'ParabolaVertex':
        a,b,c = (x[n] for n in ('a','b','c'))
        return [-float(b)/(2.0*a), (float(4*a)*c-float(b)*b)/(4.0*a)]
    if p == 'CountOddSquares':
        def squares(n): return 0 if n < 0 else min(math.isqrt(n),46340)+1
        return 0 if x['n'] > x['m'] else squares(x['m'])-squares(x['n']-1)
    if p == 'CountUnsetBits':
        return int32(sum(bin(i)[2:].count('0') for i in range(1,x['n']+1)))
    if p == 'NextPowerOf2':
        return 1 if x['n'] <= 1 else 1 << (x['n']-1).bit_length()
    if p == 'SqrtRoot':
        n = x['num']
        if n < 0: return -1
        lo,hi = 0,n
        for _ in range(64):
            if lo > hi: return hi
            mid = lo+(hi-lo)//2
            sq = int32(mid*mid)
            if sq == n: return mid
            if sq < n: lo = mid+1
            else: hi = mid-1
        raise ValueError('Frozen root recursion did not terminate')
    if p in {'Fibonacci','NewmanPrime','CountWays'}:
        n = x['n']
        if p == 'CountWays':
            a,b = [1,0],[0,1]
            for k in range(2,n+1): a.append(a[k-2]+2*b[k-1]); b.append(a[k-1]+b[k-2])
            return int32(a[n])
        a,b = (0,1) if p == 'Fibonacci' else (1,1)
        for _ in range(n): a,b = b, a+(b if p == 'Fibonacci' else 2*b)
        return int32(a)
    if p == 'DealnnoyNum':
        @lru_cache(None)
        def d(n,m): return 1 if min(n,m) <= 0 else d(m-1,n)+d(m-1,n-1)+d(m,n-1)
        return int32(d(x['n'],x['m']))
    if p == 'MaximumSegments':
        n = x['n']; dp = [0]
        for k in range(1,n+1): dp.append(max([dp[k-v]+1 for v in (x['a'],x['b'],x['c']) if v <= k and dp[k-v] >= 0] or [-1]))
        return dp[n]
    if p == 'MaxVolume':
        s = x['s']
        return max([0]+[int32(l*b*(s-l-b)) for l in range(1,s+1) for b in range(1,s-l+2)])
    if p == 'SumOfPrimes':
        return sum(n for n in range(2,x['n']+1) if all(n%d for d in range(2,math.isqrt(n)+1)))
    if p == 'MinCost':
        a,m,n = x['cost'],x['m'],x['n']; dp = {}
        for r in range(m+1):
            for c in range(n+1):
                prev = min([dp[r-1,c-1],dp[r-1,c],dp[r,c-1]]) if r and c else dp[r-1,0] if r else dp[0,c-1] if c else 0
                dp[r,c] = int32(prev+a[r][c])
        return dp[m,n]
    if p == 'MaxDifference':
        return max([0]+[int32(abs(int32(row[0]-row[1]))) for row in x['testArray'][:-1]])
    if p == 'MinCoins':
        v,m = x['v'],x['m']
        if v == 0: return 0
        if v < 0 or m <= 0: return MAXINT
        dp = [0]
        for k in range(1,v+1): dp.append(min([dp[k-c]+1 for c in x['coins'][:m] if c <= k and dp[k-c] < MAXINT] or [MAXINT]))
        return dp[v]
    if p == 'MinJumps':
        a,n = x['arr'],x['n']; dp = [0]
        for i in range(1,n):
            dp.append(min([int32(dp[j]+1) for j in range(i) if int32(a[j]+j) >= i] or [MAXINT]))
        return dp[-1]
    if p in {'FindPeak','LeftInsertion'}:
        a = x.get('a',x.get('arr')); lo,hi = 0, (len(a)-1 if p == 'LeftInsertion' else x['n']-1)
        while lo < hi if p == 'FindPeak' else lo <= hi:
            mid = (lo+hi)//2
            if p == 'FindPeak':
                if a[mid] < a[mid+1]: lo = mid+1
                else: hi = mid
            elif a[mid] == x['x']: return mid
            elif a[mid] < x['x']: lo = mid+1
            else: hi = mid-1
        return lo
    if p == 'SumRangeList': return int32(sum(x['nums'][x['m']:x['n']+1])) if x['m'] <= x['n'] else 0
    if p == 'DiffEvenOdd':
        a = x['array']; even = next((v for v in a if v%2 == 0),-1); odd = next((v for v in a if v%2 and v != -1),-1)
        return int32(even-odd)
    if p == 'TupleToInt':
        value = 0
        for v in x['nums']: value = int32(value*10+v)
        return value
    if p == 'OddLengthSum':
        a = x['arr']; n = len(a)
        return int32(sum(((k*(n-k+1)+1)//2)*v for k,v in enumerate(a,1)))
    if p == 'SumOfSubarrayProd':
        a,n = x['arr'],x['n']; total = 0
        for i in range(max(0,n)):
            product = 1
            for v in a[i:n]: product *= v; total += product
        return int32(total)
    if p == 'MaxProduct':
        a = x['arr'][:x['n']]; dp = []
        for i,v in enumerate(a): dp.append(max([v]+[int32(dp[j]*v) for j in range(i) if v > a[j]]))
        return max(dp)
    if p == 'MaxSumOfThreeConsecutive':
        a = x['arr'][:x['n']]; dp = [a[0]]
        if len(a)>1: dp.append(int32(a[0]+a[1]))
        if len(a)>2: dp.append(max(dp[1],int32(a[1]+a[2]),int32(a[0]+a[2])))
        for k in range(3,len(a)): dp.append(max(dp[k-1],int32(dp[k-2]+a[k]),int32(a[k]+a[k-1]+dp[k-3])))
        return dp[-1]
    if p == 'MaxSumSubseq':
        a = x['a']; dp = [0]+([a[0]] if a else [])
        for v in a[1:]: dp.append(max(dp[-1],int32(dp[-2]+v)))
        return dp[-1]
    if p in {'CountingSort','RadixSort'}: return sorted(next(iter(x.values())))
    if p == 'CombSort':
        a = list(x['nums']); gap = len(a)
        while True:
            gap = int(gap/1.3)
            for i in range(len(a)-gap):
                if a[i] > a[i+gap]: a[i],a[i+gap] = a[i+gap],a[i]
            if gap == 0: return a
    if p == 'MoveFirst':
        a = x['testArray']; return a[-1:]+a[:-1] if a else a
    if p == 'MultiplyElements': return [int32(a*b) for a,b in zip(x['testTup'],x['testTup'][1:])]
    if p == 'PairWise': return [list(v) for v in zip(x['l1'],x['l1'][1:])]
    if p == 'SumList': return [int32(a+b) for a,b in zip(x['arr1'],x['arr2'])]
    raise ValueError(p)


def violations(p, x, observation):
    """Identify a failed concrete frozen clause, not mere baseline divergence."""
    failed = []
    actual = observation['result']
    want = expected(p,x)
    if actual != want: failed.append('frozen_return_value_or_array_contents')
    for kind,name in parameters(p):
        if kind.startswith('J') and p not in ARRAY_MUTATORS and observation['state'][name] != x[name]:
            failed.append('assigns_input_frame:'+name)
    aliases = observation.get('aliases',{})
    if p in ARRAY_MUTATORS and not aliases.get(next(iter(x))): failed.append('result_identity')
    if p == 'MoveFirst':
        a = x['testArray']
        if a == [] and not aliases.get('testArray'): failed.append('empty_result_identity')
        if a and aliases.get('testArray'): failed.append('fresh_result')
    if p in {'CountingSort','SumList','MultiplyElements','PairWise','FindPoints','ParabolaVertex'} and any(aliases.values()):
        failed.append('fresh_result')
    if p == 'PairWise' and not observation.get('rows_separate',False): failed.append('result_rows_separated')
    if p == 'CountOddSquares':
        if x['m'] == MAXINT: failed.append('ensures_m_below_INT32_MAX')
        if x['n'] < 0 and x['n'] <= x['m'] and observation['errno'] != observation['EDOM']: failed.append('errno_EDOM')
        if (x['n'] >= 0 or x['n'] > x['m']) and observation['errno'] != 0: failed.append('errno_frame')
    if p in {'CountUnsetBits','SqrtRoot','MaxVolume'} and next(iter(x.values())) == MAXINT: failed.append('ensures_input_below_INT32_MAX')
    if p == 'NextPowerOf2' and x['n'] > 2**30: failed.append('ensures_n_limit')
    if p == 'FindPeak' and x['n'] > 1 and type(actual) is int and 0 <= actual < x['n']:
        a,n = x['arr'],x['n']
        if actual and a[actual] < a[actual-1] or actual<n-1 and a[actual] < a[actual+1]: failed.append('peak_neighbors')
    if p == 'LeftInsertion' and type(actual) is int:
        a = x['a']
        if a == sorted(a) and (any(v>x['x'] for v in a[:actual]) or any(v<x['x'] for v in a[actual:])): failed.append('sorted_partition')
    return failed


def candidates(p):
    """Deterministic bounded search across the complete program population."""
    rng = random.Random(726)
    params = parameters(p)
    arrays = [[],[0],[-1],[1],[2],[1,0],[0,1],[2,1],[-1,0,1],[3,1,2],[1,1,1],[4,3,2,1],[-2,-1],[-2**31,MAXINT]]
    arrays += [list(v) for n in range(2,5) for v in itertools.product((-1,0,1),repeat=n)]
    arrays += [[rng.choice((-3,-1,0,1,2,4,MAXINT,-2**31)) for _ in range(rng.randrange(1,8))] for _ in range(60)]
    scalar_names = [name for kind,name in params if kind == 'int32_t']
    array_names = [name for kind,name in params if kind.startswith('J')]
    rows = []
    if not array_names:
        small = (-2,-1,0,1,2,3,4,7)
        rows += [dict(zip(scalar_names,v)) for v in itertools.product(small,repeat=len(params))]
        for _ in range(120): rows.append({n:rng.choice(small+(-2**31,MAXINT,16,31,32,63)) for n in scalar_names})
        if p == 'OddBitSetNumber': rows += [{'n':int32(1<<b)} for b in range(32)]
        if p == 'NextPowerOf2': rows += [{'n':v} for v in (8,9,15,16,17,2**30)]
        if p == 'SqrtRoot': rows += [{'num':v} for v in (16,17,46340,65536)]
    elif p in {'CountList','MaxDifference','MinCost'}:
        name = array_names[0]
        matrices = [[],[[]],[[0]],[[1,2]],[[1,2],[3,4]],[[1,0],[0,1]],[[2,-1],[4,3],[0,0]]]
        matrices += [[[rng.choice((-2,-1,0,1,2,4)) for _ in range(c)] for _ in range(r)] for r in range(1,5) for c in range(1,5) for _ in range(5)]
        if p == 'CountList': matrices += [[[0]*n for n in ns] for count in range(5) for ns in itertools.product((0,1,2),repeat=count)]
        for a in matrices:
            if p == 'MinCost':
                for m,row in enumerate(a):
                    for n in range(len(row)): rows.append({name:a,'m':m,'n':n})
            else: rows.append({name:a})
    elif p == 'SumList':
        rows += [{'arr1':a,'arr2':b} for a in arrays[:14] for b in arrays[:14]]
        rows += [{'arr1':a,'arr2':arrays[rng.randrange(len(arrays))]} for a in arrays]
    else:
        name = array_names[0]
        for a in arrays:
            if not scalar_names: rows.append({name:a})
            elif p == 'LeftInsertion':
                rows += [{name:a,'x':v} for v in (-2,-1,0,1,2,3,4)]
            elif p == 'SumRangeList':
                rows += [{name:a,'m':m,'n':n} for m in range(-1,len(a)+1) for n in range(-1,len(a)+1)]
            elif p == 'MinCoins':
                rows += [{name:a,'m':m,'v':v} for m in range(min(len(a),3)+1) for v in (-1,0,1,2,3,4,7)]
            else:
                nname = scalar_names[0]
                rows += [{name:a,nname:v} for v in sorted({-1,0,1,len(a)-1,len(a),len(a)+1})]
        if p in {'MoveFirst','MaxSubArraySum','SumOfSubarrayProd','FindPeak','MinJumps','MinCoins','SumRangeList'}:
            extra = {'MaxSubArraySum':{'size':0},'SumOfSubarrayProd':{'n':0},'FindPeak':{'n':0},'MinJumps':{'n':1},'MinCoins':{'m':0,'v':0},'SumRangeList':{'m':1,'n':0}}.get(p,{})
            rows.insert(0,{name:None,**extra})
    seen,result = set(),[]
    for row in rows:
        key = json.dumps(row,sort_keys=True)
        if key not in seen and admissible(p,row) and searchable(p,row):
            seen.add(key); result.append(row)
    return result


def saved_test_candidates(p):
    """Reuse independent first calls from the experiment's existing tests."""
    from .manifest import REPO
    path=REPO/'differential_testing/generation/test_inputs-653ade686f.json'
    result=[]
    for test in json.loads(path.read_text())['tests']:
        steps=test.get('steps',[])
        if not steps or steps[0]['class']!=p or steps[0]['function']!=CONTRACTS[p]['entry']:
            continue
        fixtures={v['id']:v['value'] for v in test.get('fixtures',[]) if 'value' in v}
        args=steps[0]['arguments']
        if len(args)!=len(parameters(p)): continue
        try: row={name:arg['value'] if 'value' in arg else fixtures[arg['ref']] for (_,name),arg in zip(parameters(p),args)}
        except KeyError: continue
        if admissible(p,row) and searchable(p,row): result.append((row,'saved_test:'+test['id']))
    return result
