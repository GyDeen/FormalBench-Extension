
import java.io.*;
import java.lang.*;
import java.math.*;

class SumPairs {

	public static int sumPairs(int[] arr, int n) {
		int sum = 0;
		int total = 0;
		while (total < n - 1) {
			for (int j = total + 1; j < n; j++) {
				sum += Math.abs(arr[total] - arr[j]);
			}
			total++;
		}
		return sum;
	}
}
