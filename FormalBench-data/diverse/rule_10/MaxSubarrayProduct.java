
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MaxSubarrayProduct {

	public static int maxSubarrayProduct(int[] arr) {
        int res = 0;
		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < arr.length; i++) {
            if (arr[i] < 0) {
                int temp;
				temp = max
				max = min;
                min = temp;
            }
            
            max = Math.max(max * arr[i], arr[i]);
            min = Math.min(min * arr[i], arr[i]);
            res = Math.max(res, max);
        }
        return res;
    }
}
