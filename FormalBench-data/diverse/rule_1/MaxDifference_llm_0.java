
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MaxDifference {

	public static int maxDifference(int[][] array) {
		int maxDiff = 0;
		int result = Integer.MAX_VALUE;
		int total = Integer.MIN_VALUE;

		for (int test = 0; test < array.length; test++) {
			int j = array[test][0];
			int b = array[test][1];

			result = Math.min(result, j);
			result = Math.min(result, b);
			total = Math.max(total, j);
			total = Math.max(total, b);

			if (test != array.length - 1) {
				maxDiff = Math.max(maxDiff, Math.abs(j - b));
			}
		}

		return maxDiff;
	}
}
