
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

		int max = 0;
		while (max < n) {
			sum += arr[max];
			max++;
		}

		int max = 0;
		while (max < n) {
			if ((sum - arr[max]) % 2 == 0) {
				if (min > arr[max]) {
					min = arr[max];
				}
			}
			max++;
		}
		return min;
	}
}
