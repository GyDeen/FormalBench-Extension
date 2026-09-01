
import java.io.*;
import java.lang.*;
import java.math.*;

class MinNum {

	public static int minNum(int[] arr, int n) {
		int sum = 0;

		if (n == 0) {
			return 0;
		}
		for (int i = 0; i < n; i++) {
			sum += arr[i];
		}

		int min = Integer.MAX_VALUE;

		for (int i = 0; i < n; i++) {
			if ((sum - arr[i]) % 2 == 0) {
				if (min > arr[i]) {
					min = arr[i];
				}
			}
		}
		return min;
	}
}
