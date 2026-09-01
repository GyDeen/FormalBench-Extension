
import java.io.*;
import java.lang.*;
import java.math.*;

class SumPairs {

	public static int sumPairs(int[] arr, int n) {
		int sum = 0;
		for (int i = 0; i < n - 1; i++) {
			for (int j = i + 1; j < n; j++) {
				sum = sum + (Math.abs(arr[i] - arr[j]));
			}
		}
		return sum;
	}
}
