
import java.io.*;
import java.lang.*;
import java.math.*;

class SumOfSubarrayProd {
    
    /*@ public normal_behavior
      @ requires end <= start || (a != null && 0 <= start && end <= a.length);
      @ ensures \result == (end <= start ? (\bigint)1 : fb_product(a, start, end - 1) * a[end - 1]);
      @ measured_by end > start ? (\bigint)end - start : 0;
      @ model public static pure \bigint fb_product(int[] a, int start, int end);
      @*/
    /*@ public normal_behavior
      @ requires end <= start || (a != null && 0 <= start && end <= a.length);
      @ ensures \result == (end <= start ? (\bigint)0 : fb_row_sum(a, start, end - 1) + fb_product(a, start, end));
      @ measured_by end > start ? (\bigint)end - start : 0;
      @ model public static pure \bigint fb_row_sum(int[] a, int start, int end);
      @*/
    /*@ public normal_behavior
      @ requires rows >= 0 && (rows == 0 || (a != null && rows <= n <= a.length));
      @ ensures \result == (rows == 0 ? (\bigint)0 : fb_rows_sum(a, rows - 1, n) + fb_row_sum(a, rows - 1, n));
      @ measured_by rows;
      @ model public static pure \bigint fb_rows_sum(int[] a, int rows, int n);
      @*/
    /*@ public normal_behavior
      @ ensures \result == ((x % 4294967296L + 6442450944L) % 4294967296L) - 2147483648L;
      @ model public static pure \bigint fb_wrap(\bigint x);
      @*/
    /*@ public normal_behavior
      @ requires n <= 0 || (arr != null && n <= arr.length);
      @ assignable \nothing;
      @ ensures \result == fb_wrap(fb_rows_sum(arr, n > 0 ? n : 0, n));
      @ also
      @ public exceptional_behavior
      @ requires n > 0 && arr == null;
      @ assignable \nothing;
      @ signals_only NullPointerException;
      @ also
      @ public exceptional_behavior
      @ requires n > 0 && arr != null && n > arr.length;
      @ assignable \nothing;
      @ signals_only ArrayIndexOutOfBoundsException;
      @*/
    //@ code_java_math
    public static int sumOfSubarrayProd(int[] arr, int n) {
        int sum = 0;
        /*@ loop_invariant 0 <= i <= (n > 0 ? n : 0);
          @ loop_invariant sum == fb_wrap(fb_rows_sum(arr, i, n));
          @ loop_writes i, sum;
          @ decreases (\bigint)n - i;
          @*/
        for (int i = 0; i < n; i++) {
            int product = 1;
            /*@ loop_invariant i <= j <= n;
              @ loop_invariant product == fb_wrap(fb_product(arr, i, j));
              @ loop_invariant sum == fb_wrap(fb_rows_sum(arr, i, n) + fb_row_sum(arr, i, j));
              @ loop_writes j, product, sum;
              @ decreases n - j;
              @*/
            for (int j = i; j < n; j++) {
                product *= arr[j];  // Multiply with the next element in subarray
                sum += product;     // Add the product to the sum
            }
        }
        return sum;
    }
}

