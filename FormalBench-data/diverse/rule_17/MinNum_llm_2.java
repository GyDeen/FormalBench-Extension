
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

		for (int i = 0; i < n; i++) {
			sum += arr[i];
		}

		for (int i = 0; i < n; i++) {
			int end = (sum - arr[i]) % 2;
			if (end == 0) {
				if (min > arr[i]) {
					min = arr[i];
				}
			}
		}
		return min;
	}
}
