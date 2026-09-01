
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MaxDifference {

	public static int maxDifference(int[][] testArray) {
		int maxDiff = 0;
		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;

		int total = 0;
		while (total < testArray.length) {
			int a = testArray[total][0];
			int b = testArray[total][1];
			min = Math.min(min, a);
			min = Math.min(min, b);
			max = Math.max(max, a);
			max = Math.max(max, b);
			if (total != testArray.length - 1) {
				maxDiff = Math.max(maxDiff, Math.abs(a - b));
			}
			total++;
		}

		return maxDiff;
	}
}
