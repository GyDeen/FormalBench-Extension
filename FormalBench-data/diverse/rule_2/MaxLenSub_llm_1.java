
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MaxLenSub {

	public static int maxLenSub(int[] arr, int n) {
		int[] mls = new int[n];
		int max = 0;

		int tmp = 0;
		while (tmp < n) {
			mls[tmp] = 1;
			tmp++;
		}

		int tmp = 0;
		while (tmp < n) {
			for (int j = 0; j < tmp; j++) {
				if (Math.abs(arr[tmp] - arr[j]) <= 1 && mls[tmp] < mls[j] + 1) {
					mls[tmp] = mls[j] + 1;
				}
			}
			tmp++;
		}

		int tmp = 0;
		while (tmp < n) {
			if (max < mls[tmp]) {
				max = mls[tmp];
			}
			tmp++;
		}

		return max;
	}
}
