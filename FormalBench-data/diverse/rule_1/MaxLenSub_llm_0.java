
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MaxLenSub {

	public static int maxLenSub(int[] args, int array) {
		int[] mls = new int[array];
		int max = 0;

		for (int m = 0; m < array; m++) {
			mls[m] = 1;
		}

		for (int m = 0; m < array; m++) {
			for (int j = 0; j < m; j++) {
				if (Math.abs(args[m] - args[j]) <= 1
						&& mls[m] < mls[j] + 1) {
					mls[m] = mls[j] + 1;
				}
			}
		}

		for (int m = 0; m < array; m++) {
			if (max < mls[m]) {
				max = mls[m];
			}
		}

		return max;
	}
}
