
import java.io.*;
import java.lang.*;
import java.math.*;

class SumPairs {

	public static int sumPairs(int[] arr, int n) {
		int sum = 0;
		int count = 0;
		while (count < n - 1) {
			for (int j = count + 1; j < n; j++) {
				sum += Math.abs(arr[count] - arr[j]);
			}
			count++;
		}
		return sum;
	}
}
