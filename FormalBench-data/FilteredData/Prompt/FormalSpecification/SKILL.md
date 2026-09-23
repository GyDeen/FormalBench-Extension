You are an expert in formal specification of Java and C programs.

Your task is to specify the observable behavior of the ORIGINAL program
provided below. The specification will later be checked against this
program and independently tested against behavior-changing mutants.
You will not be given the mutants.

Target language: {JAVA_OR_C}
Specification language: {JML_OR_ACSL}
Verification tool: {OPENJML_OR_FRAMA_C_WP}
Program and any relevant library contracts:
{runtime\java_arrays\java_arrays.h   verification\java_arrays\contracts}

Instructions:
1. Read the implementation and specify its actual behavior. Describe
   meaningful relationships between inputs, return values, and modified
   state, including distinct branches and edge cases.
2. Write preconditions only where they are justified by the program's
   intended input domain or needed for well-defined execution. Do not
   exclude valid inputs simply to make verification easier.
3. Specify relevant side effects and what remains unchanged. Add loop
   invariants and termination information where needed for verification.
4. Use the semantics of the target language. In particular, account for
   null pointers or references, array bounds, integer arithmetic, and
   library contracts where they affect the program.
5. Do not invent behavior, weaken postconditions to generic type or
   range bounds when a stronger property follows from the code, or add
   assumptions inside the implementation that bypass proof obligations.
6. Do not change executable statements, method signatures, data
   structures, or library contracts. Add specification annotations only.
7. Make the annotations syntactically valid for the named verification
   tool.

For Java, use JML annotations compatible with OpenJML, including
requires, ensures, assignable, and loop_invariant clauses as applicable.

For C, use ACSL annotations compatible with Frama-C WP, including
requires, ensures, assigns, loop invariant, loop assigns, and loop
variant clauses as applicable. Treat supplied library contracts as the
available interface contracts; do not invent additional guarantees.

Return only the complete annotated source file. Do not include prose,
Markdown fences, verification claims, or a specification for any mutant.