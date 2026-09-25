
import java.io.*;
import java.lang.*;
import java.math.*;

class DiffEvenOdd {
    
    /*@ public normal_behavior
      @ requires a != null && 0 <= k <= a.length;
      @ ensures \result == (k == 0 ? -1 : fb_first(a, k - 1, odd) != -1 ? fb_first(a, k - 1, odd) : ((odd ? a[k - 1] % 2 != 0 && a[k - 1] != -1 : a[k - 1] % 2 == 0) ? a[k - 1] : -1));
      @ measured_by k;
      @ model public static pure int fb_first(int[] a, int k, boolean odd);
      @*/
    /*@ public normal_behavior
      @ requires array != null;
      @ assignable \nothing;
      @ 
      @ ensures \result == \java_math(fb_first(array, array.length, false) - fb_first(array, array.length, true));
      @ 
      @ also
      @ public exceptional_behavior
      @ requires array == null;
      @ assignable \nothing;
      @ signals_only NullPointerException;
      @*/
    //@ code_java_math
    public static int diffEvenOdd(int[] array) {
        int firstEven = -1;
        int firstOdd = -1;

        /*@ loop_invariant 0 <= \count <= array.length;
          @ loop_invariant firstEven == fb_first(array, \count, false) && firstOdd == fb_first(array, \count, true);
          @ loop_writes firstEven, firstOdd;
          @ decreases array.length - \count;
          @*/
        for (int num : array) {
            if (firstEven == -1 && num % 2 == 0) {
                firstEven = num;
            }
            if (firstOdd == -1 && num % 2 != 0) {
                firstOdd = num;
            }
            if (firstEven != -1 && firstOdd != -1) {
                break;
            }
        }

        return (firstEven - firstOdd);
    }
}

