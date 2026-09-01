
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MaxSubArraySumRepeated {

	public static int maxSubArraySumRepeated(int[] a, int n, int k) {
		int max = 0;
		int sum = 0;

		for (int i = 0; i < n; i++) {
			sum += a[i];
		}

		max = sum;

		for (int i = 0; i < n; i++) {
			if (!(i >= k))
				;
			else {
				sum -= a[i - k];
			}

			if (!(sum < 0))
				;
			else {
				sum = 0;
			}
			sum += a[i];
			max = Math.max(max, sum);
		}

		return max;
	}
}
