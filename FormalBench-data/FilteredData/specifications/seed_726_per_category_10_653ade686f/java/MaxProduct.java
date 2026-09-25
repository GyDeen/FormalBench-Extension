
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MaxProduct {
    
    /*@ public normal_behavior
      @ ensures \result == ((x % 4294967296L + 6442450944L) % 4294967296L) - 2147483648L;
      @ model public static pure \bigint fb_wrap(\bigint x);
      @*/
    /*@ public normal_behavior
      @ ensures \result == (a >= b ? a : b);
      @ model public static pure \bigint fb_max(\bigint a, \bigint b);
      @*/
    /*@ public normal_behavior
      @ requires a != null && 0 <= j <= i < a.length;
      @ ensures \result == (j == 0 ? a[i] : fb_max(fb_product_end(a, i, j - 1), a[i] > a[j - 1] ? fb_wrap(fb_product_end(a, j - 1, j - 1) * a[i]) : a[i]));
      @ measured_by (\bigint)i * (a.length + 1) + j;
      @ model public static pure \bigint fb_product_end(int[] a, int i, int j);
      @*/
    /*@ public normal_behavior
      @ requires a != null && 1 <= k <= a.length;
      @ ensures \result == (k == 1 ? a[0] : fb_max(fb_product_best(a, k - 1), fb_product_end(a, k - 1, k - 1)));
      @ measured_by k;
      @ model public static pure \bigint fb_product_best(int[] a, int k);
      @*/
    /*@ public normal_behavior
      @ requires arr != null && 1 <= n <= arr.length;
      @ assignable \nothing;
      @ ensures \result == fb_product_best(arr, n);
      @*/
    //@ code_java_math
    public static int maxProduct(int[] arr, int n) {
        int[] mpis = new int[n];
        /*@ loop_invariant 0 <= i <= n;
          @ loop_invariant (\forall int k; 0 <= k < i; mpis[k] == arr[k]);
          @ loop_writes i, mpis[*];
          @ decreases n - i;
          @*/
        for (int i = 0; i < n; i++) {
            mpis[i] = arr[i];
        }
        /*@ loop_invariant 1 <= i <= n;
          @ loop_invariant (\forall int k; 0 <= k < i; mpis[k] == fb_product_end(arr, k, k));
          @ loop_invariant (\forall int k; i <= k < n; mpis[k] == arr[k]);
          @ loop_writes i, mpis[*];
          @ decreases n - i;
          @*/
        for (int i = 1; i < n; i++) {
            /*@ loop_invariant 0 <= j <= i;
              @ loop_invariant mpis[i] == fb_product_end(arr, i, j);
              @ loop_writes j, mpis[i];
              @ decreases i - j;
              @*/
            for (int j = 0; j < i; j++) {
                if (arr[i] > arr[j] && mpis[i] < (mpis[j] * arr[i])) {
                    mpis[i] = mpis[j] * arr[i];
                }
            }
        }
        int max = mpis[0];
        /*@ loop_invariant 1 <= i <= n;
          @ loop_invariant max == fb_product_best(arr, i);
          @ loop_writes i, max;
          @ decreases n - i;
          @*/
        for (int i = 1; i < n; i++) {
            if (mpis[i] > max) {
                max = mpis[i];
            }
        }
        return max;
    }
}

