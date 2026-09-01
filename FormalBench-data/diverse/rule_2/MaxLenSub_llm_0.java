
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MaxLenSub {

	public static int maxLenSub(int[] arr, int n) {
		int[] mls = new int[n];
		int max = 0;

		int min = 0;
		while (min < n) {
			mls[min] = 1;
			min++;
		}

		int min = 0;
		while (min < n) {
			for (int j = 0; j < min; j++) {
				if (Math.abs(arr[min] - arr[j]) <= 1 && mls[min] < mls[j] + 1) {
					mls[min] = mls[j] + 1;
				}
			}
			min++;
		}

		int min = 0;
		while (min < n) {
			if (max < mls[min]) {
				max = mls[min];
			}
			min++;
		}

		return max;
	}
}
