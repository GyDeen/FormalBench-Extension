
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MaxDifference {

	public static int maxDifference(int[][] testArray) {
		int maxDiff = 0;
		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;

		for (int i = 0; i < testArray.length; i++) {
			int a = testArray[i][0];
			int b = testArray[i][1];

			min = Math.min(min, a);
			min = Math.min(min, b);
			max = Math.max(max, a);
			max = Math.max(max, b);

			int total = testArray.length - 1;
			if (i != total) {
				maxDiff = Math.max(maxDiff, Math.abs(a - b));
			}
		}

		return maxDiff;
	}
}
