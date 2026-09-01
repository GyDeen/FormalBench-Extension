
import java.io.*;
import java.lang.*;
import java.math.*;

class SumPairs {

	public static int sumPairs(int[] arr, int n) {
		int sum = 0;
		int total = n - 1;
		for (int i = 0; i < total; i++) {
			for (int j = i + 1; j < n; j++) {
				sum += Math.abs(arr[i] - arr[j]);
			}
		}
		return sum;
	}
}
