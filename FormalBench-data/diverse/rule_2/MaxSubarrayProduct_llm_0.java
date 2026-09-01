
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MaxSubarrayProduct {

	public static int maxSubarrayProduct(int[] arr) {
		int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE, res = 0;

		int ret = 0;
		while (ret < arr.length) {
			if (arr[ret] < 0) {
				int temp = max;
				max = min;
				min = temp;
			}
			max = Math.max(max * arr[ret], arr[ret]);
			min = Math.min(min * arr[ret], arr[ret]);
			res = Math.max(res, max);
			ret++;
		}
		return res;
	}
}
