
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MaxLenSub {

	public static int maxLenSub(int[] arr, int n) {
		int[] mls = new int[n];
		int max = 0;

		int total = 0;
		while (total < n) {
			mls[total] = 1;
			total++;
		}

		int total = 0;
		while (total < n) {
			for (int j = 0; j < total; j++) {
				if (Math.abs(arr[total] - arr[j]) <= 1 && mls[total] < mls[j] + 1) {
					mls[total] = mls[j] + 1;
				}
			}
			total++;
		}

		int total = 0;
		while (total < n) {
			if (max < mls[total]) {
				max = mls[total];
			}
			total++;
		}

		return max;
	}
}
