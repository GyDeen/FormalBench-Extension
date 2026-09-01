
import java.io.*;
import java.lang.*;
import java.math.*;

class MinNum {

	public static int minNum(int[] arr, int n) {
		if (0 == n) {
			return 0;
		}

		int sum = 0;
		int min = Integer.MAX_VALUE;

		for (int i = 0; i < n; i++) {
			sum += arr[i];
		}

		for (int i = 0; i < n; i++) {
			if (0 == (sum - arr[i]) % 2) {
				if (min > arr[i]) {
					min = arr[i];
				}
			}
		}
		return min;
	}
}
