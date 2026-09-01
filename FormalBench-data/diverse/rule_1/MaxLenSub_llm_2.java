
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MaxLenSub {

	public static int maxLenSub(int[] args, int array) {
		int[] mls = new int[array];
		int max = 0;

		for (int index = 0; index < array; index++) {
			mls[index] = 1;
		}

		for (int index = 0; index < array; index++) {
			for (int j = 0; j < index; j++) {
				if (Math.abs(args[index] - args[j]) <= 1
						&& mls[index] < mls[j] + 1) {
					mls[index] = mls[j] + 1;
				}
			}
		}

		for (int index = 0; index < array; index++) {
			if (max < mls[index]) {
				max = mls[index];
			}
		}

		return max;
	}
}
