import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;


class NextPowerOf2 {
    
    /*@ public normal_behavior
      @ assignable \nothing;
      @ diverges n > 1073741824;
      @ ensures n <= 1073741824;
      @ ensures 1 <= \result <= 1073741824 && \result >= n;
      @ ensures (\result & (\result - 1)) == 0;
      @ ensures \result == 1 || \result / 2 < n;
      @*/
    //@ code_java_math
    public static int nextPowerOf2(int n) {
        if (n == 0) {
            return 1;
        }
        int i = 1;
        /*@ loop_invariant i == 0 || i == Integer.MIN_VALUE || (1 <= i <= 1073741824 && (i & (i - 1)) == 0);
          @ loop_invariant n <= 1073741824 ==> 1 <= i <= 1073741824;
          @ loop_invariant n <= 1073741824 ==> i == 1 || i / 2 < n;
          @ loop_writes i;
          @*/
        while (i < n) {
            i = i << 1;
        }
        return i;
    }
}

