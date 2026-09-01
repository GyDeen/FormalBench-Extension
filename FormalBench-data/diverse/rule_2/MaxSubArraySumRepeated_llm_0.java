
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MaxSubArraySumRepeated {

	public static int maxSubArraySumRepeated(int[] a, int n, int k) {
		int max = 0;
		int sum = 0;

		int min = 0;
		while (min < n) {
			sum += a[min];
			min++;
		}

		max = sum;

		int min = 0;
		while (min < n) {
			if (min >= k) {
				sum -= a[min - k];
			}
			if (sum < 0) {
				sum = 0;
			}
			sum += a[min];
			max = Math.max(max, sum);
			min++;
		}

		return max;
	}
}
