
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MaxSubarrayProduct {

	public static int maxSubarrayProduct(int[] arr) {
		int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE, res = 0;

		int r = 0;
		while (r < arr.length) {
			if (arr[r] < 0) {
				int temp = max;
				max = min;
				min = temp;
			}
			max = Math.max(max * arr[r], arr[r]);
			min = Math.min(min * arr[r], arr[r]);
			res = Math.max(res, max);
			r++;
		}
		return res;
	}
}
