import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;


class DealnnoyNum {
    
    /*@ public normal_behavior
      @ ensures \result == ((x % 4294967296L + 6442450944L) % 4294967296L) - 2147483648L;
      @ model public static pure \bigint fb_wrap(\bigint x);
      @*/
    /*@ public normal_behavior
      @ requires n == 0 || m == 0 || (n > 0 && m > 0);
      @ ensures \result == (n == 0 || m == 0 ? 1 : fb_delannoy(m - 1, n) + fb_delannoy(m - 1, n - 1) + fb_delannoy(m, n - 1));
      @ measured_by (n > 0 && m > 0 ? n + m : 0);
      @ model public static pure \bigint fb_delannoy(\bigint n, \bigint m);
      @*/
    /*@ public normal_behavior
      @ requires n == 0 || m == 0 || (n > 0 && m > 0);
      @ assignable \nothing;
      @ ensures \result == fb_wrap(fb_delannoy(n, m));
      @ measured_by (n > 0 && m > 0 ? (\bigint)n + m : 0);
      @*/
    //@ code_java_math
    public static int dealnnoyNum(int n, int m) {
        if (m == 0 || n == 0) {
            return 1;
        }
        return dealnnoyNum(m - 1, n) + dealnnoyNum(m - 1, n - 1) + dealnnoyNum(m, n - 1);
    }
}

