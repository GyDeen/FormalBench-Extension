import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;


class NewmanPrime {
    
    /*@ public normal_behavior
      @ ensures \result == ((x % 4294967296L + 6442450944L) % 4294967296L) - 2147483648L;
      @ model public static pure \bigint fb_wrap(\bigint x);
      @*/
    /*@ public normal_behavior
      @ requires n >= 0;
      @ ensures \result == (n <= 1 ? 1 : 2 * fb_value(n - 1) + fb_value(n - 2));
      @ measured_by n;
      @ model public static pure \bigint fb_value(\bigint n);
      @*/
    /*@ public normal_behavior
      @ requires n >= 0;
      @ assignable \nothing;
      @ ensures \result == fb_wrap(fb_value(n));
      @ measured_by n;
      @*/
    //@ code_java_math
    public static int newmanPrime(int n) {
        if (n == 0 || n == 1) {
            return 1;
        }
        return 2 * newmanPrime(n - 1) + newmanPrime(n - 2);
    }
}

