
import java.io.*;
import java.lang.*;
import java.math.*;

class MinDifference {

	public static int minDifference(int[][] tuples) {
		int minDiff = Integer.MAX_VALUE;
		int total = 0;
		while (total < tuples.length) {
			int diff = Math.abs(tuples[total][0] - tuples[total][1]);
			if (minDiff > diff) {
				minDiff = diff;
			}
			total++;
		}
		return minDiff;
	}
}
