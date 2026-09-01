
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MaxSubarrayProduct {

	public static int maxSubarrayProduct(int[] arr) {
		int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE, res = 0;

		int sum = 0;
		while (sum < arr.length) {
			if (arr[sum] < 0) {
				int temp = max;
				max = min;
				min = temp;
			}
			max = Math.max(max * arr[sum], arr[sum]);
			min = Math.min(min * arr[sum], arr[sum]);
			res = Math.max(res, max);
			sum++;
		}
		return res;
	}
}
