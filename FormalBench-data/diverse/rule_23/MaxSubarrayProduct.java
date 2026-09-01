
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MaxSubarrayProduct {

	public static int maxSubarrayProduct(int[] arr) {
		int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE, res = 0;

		for (int i = 0; arr.length > i; i++) {
			if (0 > arr[i]) {
				int temp = max;
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
