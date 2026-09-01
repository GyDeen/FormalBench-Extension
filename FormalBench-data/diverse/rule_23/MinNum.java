
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

		for (int i = 0; n > i; i++) {
			sum += arr[i];
		}

		for (int i = 0; n > i; i++) {
			if ((sum - arr[i]) % 2 == 0) {
				if (arr[i] < min) {
					min = arr[i];
				}
			}
		}
		return min;
	}
}
