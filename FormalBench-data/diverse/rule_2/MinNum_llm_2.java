
import java.io.*;
import java.lang.*;
import java.math.*;

class MinNum {

	public static int minNum(int[] arr, int n) {
		if (n == 0) {
			return 0;
		}

		int sum = 0;
		int min = Integer.MAX_VALUE;

		int end = 0;
		while (end < n) {
			sum += arr[end];
			end++;
		}

		int end = 0;
		while (end < n) {
			if ((sum - arr[end]) % 2 == 0) {
				if (min > arr[end]) {
					min = arr[end];
				}
			}
			end++;
		}
		return min;
	}
}
