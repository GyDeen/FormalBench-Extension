
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MaxDifference {

	public static int maxDifference(int[][] testArray) {
		int maxDiff = 0;
		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;

		for (int i = 0; testArray.length > i; i++) {
			int a = testArray[i][0];
			int b = testArray[i][1];

			min = Math.min(min, a);
			min = Math.min(min, b);
			max = Math.max(max, a);
			max = Math.max(max, b);

			if (testArray.length - 1 != i) {
				maxDiff = Math.max(maxDiff, Math.abs(a - b));
			}
		}

		return maxDiff;
	}
}
