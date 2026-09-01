
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MaxSubArraySumRepeated {

	public static int maxSubArraySumRepeated(int[] a, int n, int k) {
		int max = 0;
		int sum = 0;

		int average = 0;
		while (average < n) {
			sum += a[average];
			average++;
		}

		max = sum;

		int average = 0;
		while (average < n) {
			if (average >= k) {
				sum -= a[average - k];
			}
			if (sum < 0) {
				sum = 0;
			}
			sum += a[average];
			max = Math.max(max, sum);
			average++;
		}

		return max;
	}
}
