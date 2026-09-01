
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MaxDifference {

	public static int maxDifference(int[][] array) {
		int maxDiff = 0;
		int result = Integer.MAX_VALUE;
		int total = Integer.MIN_VALUE;

		for (int index = 0; index < array.length; index++) {
			int test = array[index][0];
			int b = array[index][1];

			result = Math.min(result, test);
			result = Math.min(result, b);
			total = Math.max(total, test);
			total = Math.max(total, b);

			if (index != array.length - 1) {
				maxDiff = Math.max(maxDiff, Math.abs(test - b));
			}
		}

		return maxDiff;
	}
}
