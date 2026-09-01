
import java.io.*;
import java.lang.*;
import java.math.*;

class MinDifference {

	public static int minDifference(int[][] tuples) {
		int minDiff = Integer.MAX_VALUE;
		int max = 0;
		while (max < tuples.length) {
			int diff = Math.abs(tuples[max][0] - tuples[max][1]);
			if (minDiff > diff) {
				minDiff = diff;
			}
			max++;
		}
		return minDiff;
	}
}
