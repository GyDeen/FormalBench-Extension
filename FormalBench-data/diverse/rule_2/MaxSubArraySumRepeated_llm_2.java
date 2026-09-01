
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MaxSubArraySumRepeated {

	public static int maxSubArraySumRepeated(int[] a, int n, int k) {
		int max = 0;
		int sum = 0;

		int total = 0;
		while (total < n) {
			sum += a[total];
			total++;
		}

		max = sum;

		int total = 0;
		while (total < n) {
			if (total >= k) {
				sum -= a[total - k];
			}
			if (sum < 0) {
				sum = 0;
			}
			sum += a[total];
			max = Math.max(max, sum);
			total++;
		}

		return max;
	}
}
