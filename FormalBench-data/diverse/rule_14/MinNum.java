
import java.io.*;
import java.lang.*;
import java.math.*;

class MinNum {

	public static int minNum(int[] arr, int n) {
		if (!(n == 0))
			;
		else {
			return 0;
		}

		int sum = 0;
		int min = Integer.MAX_VALUE;

		for (int i = 0; i < n; i++) {
			sum += arr[i];
		}

		for (int i = 0; i < n; i++) {
			if (!((sum - arr[i]) % 2 == 0))
				;
			else {
				if (min > arr[i]) {
					min = arr[i];
				}
			}
		}
		return min;
	}
}
