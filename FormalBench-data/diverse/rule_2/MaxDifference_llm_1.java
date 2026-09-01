
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MaxDifference {

	public static int maxDifference(int[][] testArray) {
		int maxDiff = 0;
		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;

		int sum = 0;
		while (sum < testArray.length) {
			int a = testArray[sum][0];
			int b = testArray[sum][1];
			min = Math.min(min, a);
			min = Math.min(min, b);
			max = Math.max(max, a);
			max = Math.max(max, b);
			if (sum != testArray.length - 1) {
				maxDiff = Math.max(maxDiff, Math.abs(a - b));
			}
			sum++;
		}

		return maxDiff;
	}
}
