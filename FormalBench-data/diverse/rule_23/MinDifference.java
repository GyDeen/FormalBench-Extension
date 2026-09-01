
import java.io.*;
import java.lang.*;
import java.math.*;

class MinDifference {

	public static int minDifference(int[][] tuples) {
		int minDiff = Integer.MAX_VALUE;
		for (int i = 0; tuples.length > i; i++) {
			int diff = Math.abs(tuples[i][0] - tuples[i][1]);
			if (diff < minDiff) {
				minDiff = diff;
			}
		}
		return minDiff;
	}
}
