
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MaxLenSub {

	public static int maxLenSub(int[] arr, int n) {
		int[] mls = new int[n];
		int max = 0;

		for (int i = 0; n > i; i++) {
			mls[i] = 1;
		}

		for (int i = 0; n > i; i++) {
			for (int j = 0; i > j; j++) {
				if (1 >= Math.abs(arr[i] - arr[j]) && mls[j] + 1 > mls[i]) {
					mls[i] = mls[j] + 1;
				}
			}
		}

		for (int i = 0; n > i; i++) {
			if (mls[i] > max) {
				max = mls[i];
			}
		}

		return max;
	}
}
