
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

		int total = 0;
		while (total < n) {
			sum += arr[total];
			total++;
		}

		int total = 0;
		while (total < n) {
			if ((sum - arr[total]) % 2 == 0) {
				if (min > arr[total]) {
					min = arr[total];
				}
			}
			total++;
		}
		return min;
	}
}
