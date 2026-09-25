import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;


class CountUnsetBits {
    
    /*@
    public normal_behavior
      ensures \result == ((x % 4294967296L + 6442450944L) % 4294967296L) - 2147483648L;
    model public static pure \bigint fb_wrap(\bigint x);

        public normal_behavior
          requires x >= 0;
          ensures \result == (x == 0 ? 0 : fb_zeroBits(x / 2) + (x % 2 == 0 ? 1 : 0));
          measured_by x;
        model public static pure \bigint fb_zeroBits(int x);
        public normal_behavior
          ensures \result == (n <= 0 ? 0 : fb_totalZeros(n - 1) + fb_zeroBits(n));
          measured_by n > 0 ? n : 0;
        model public static pure \bigint fb_totalZeros(int n);

        public normal_behavior
          assignable \nothing;
          diverges n == Integer.MAX_VALUE;
          ensures n < Integer.MAX_VALUE;
          ensures \result == fb_wrap(fb_totalZeros(n));
    @*/
    //@ code_java_math
    public static int countUnsetBits(int n) {
        int cnt = 0;
        /*@
        loop_invariant n < Integer.MAX_VALUE ==> 1 <= i && (\bigint)i <= (n < 1 ? 1 : (\bigint)n + 1);
        loop_invariant n < Integer.MAX_VALUE ==> cnt == fb_wrap(fb_totalZeros(i - 1));
        loop_writes i, cnt;
        @*/
        for (int i = 1; i <= n; i++) {
            int temp = i;
            /*@
            loop_invariant n < Integer.MAX_VALUE ==> 0 <= temp <= i;
            loop_invariant n < Integer.MAX_VALUE ==> cnt == fb_wrap(fb_totalZeros(i) - fb_zeroBits(temp));
            loop_writes temp, cnt;
            @*/
            while (temp != 0) {
                if ((temp & 1) == 0) {
                    cnt++;
                }
                temp = temp >> 1;
            }
        }
        return cnt;
    }
}

