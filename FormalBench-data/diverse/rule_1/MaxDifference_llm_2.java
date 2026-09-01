
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MaxDifference {

	public static int maxDifference(int[][] array) {
		int maxDiff = 0;
		int test = Integer.MAX_VALUE;
		int total = Integer.MIN_VALUE;

		for (int index = 0; index < array.length; index++) {
			int j = array[index][0];
			int b = array[index][1];

			test = Math.min(test, j);
			test = Math.min(test, b);
			total = Math.max(total, j);
			total = Math.max(total, b);

			if (index != array.length - 1) {
				maxDiff = Math.max(maxDiff, Math.abs(j - b));
			}
		}

		return maxDiff;
	}
}
