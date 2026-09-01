
import java.io.*;
import java.lang.*;
import java.math.*;

class SumPairs {

	public static int sumPairs(int[] arr, int n) {
		int sum = 0;
		int end = 0;
		while (end < n - 1) {
			for (int j = end + 1; j < n; j++) {
				sum += Math.abs(arr[end] - arr[j]);
			}
			end++;
		}
		return sum;
	}
}
