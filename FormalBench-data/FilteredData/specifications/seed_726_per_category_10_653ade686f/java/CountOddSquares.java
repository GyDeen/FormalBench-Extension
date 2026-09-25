import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;


class CountOddSquares {
    
    /*@
    public normal_behavior
      ensures x < 0 ==> \result == 0;
      ensures x >= 0 ==> 1 <= \result <= 46341
        && (\bigint)(\result - 1) * (\result - 1) <= x
        && x < (\bigint)\result * \result;
    model public static pure int fb_squaresThrough(int x);

    public normal_behavior
      assignable \nothing;
      diverges m == Integer.MAX_VALUE;
      ensures m < Integer.MAX_VALUE;
      ensures \result == (n > m ? 0 : fb_squaresThrough(m) - (n <= 0 ? 0 : fb_squaresThrough(n - 1)));
      ensures 0 <= \result <= 46341;
    @*/
    //@ code_java_math
    public static int countOddSquares(int n, int m) {
        int count = 0;
        /*@
        loop_invariant m < Integer.MAX_VALUE ==> n <= i && (\bigint)i <= (n > m ? (\bigint)n : (\bigint)m + 1);
        loop_invariant m < Integer.MAX_VALUE ==> count == (i == n ? 0 : fb_squaresThrough(i - 1) - (n <= 0 ? 0 : fb_squaresThrough(n - 1)));
        loop_writes i, count;
        @*/
        for (int i = n; i <= m; i++) {
            double square = Math.sqrt(i);
            if (square % 1 == 0 && i % 1 == 0) {
                count++;
            }
        }
        return count;
    }
}

