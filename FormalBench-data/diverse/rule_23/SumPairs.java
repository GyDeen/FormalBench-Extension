
import java.io.*;
import java.lang.*;
import java.math.*;

class SumPairs {

	public static int sumPairs(int[] arr, int n) {
		int sum = 0;
		for (int i = 0; n - 1 > i; i++) {
			for (int j = i + 1; n > j; j++) {
				sum += Math.abs(arr[i] - arr[j]);
			}
		}
		return sum;
	}
}
