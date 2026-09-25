
import java.io.*;
import java.lang.*;
import java.math.*;

class SumOfSubarrayProd {
    
    /*@ public normal_behavior
      @ ensures \result == ((x % 4294967296L + 6442450944L) % 4294967296L) - 2147483648L;
      @ model public static pure \bigint fb_wrap(\bigint x);
      @*/
    /*@ public normal_behavior
      @ requires n <= 0 || (arr != null && n <= arr.length);
      @ assignable \nothing;
      @ ensures \result == fb_wrap((\sum int i; 0 <= i < n; (\sum int j; i <= j < n; (\product int k; i <= k <= j; (\bigint)arr[k]))));
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
          @ loop_invariant sum == fb_wrap((\sum int s; 0 <= s < i; (\sum int e; s <= e < n; (\product int k; s <= k <= e; (\bigint)arr[k]))));
          @ loop_writes i, sum;
          @ decreases (\bigint)n - i;
          @*/
        for (int i = 0; i < n; i++) {
            int product = 1;
            /*@ loop_invariant i <= j <= n;
              @ loop_invariant product == fb_wrap((\product int k; i <= k < j; (\bigint)arr[k]));
              @ loop_invariant sum == fb_wrap((\sum int s; 0 <= s < i; (\sum int e; s <= e < n; (\product int k; s <= k <= e; (\bigint)arr[k]))) + (\sum int e; i <= e < j; (\product int k; i <= k <= e; (\bigint)arr[k])));
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

