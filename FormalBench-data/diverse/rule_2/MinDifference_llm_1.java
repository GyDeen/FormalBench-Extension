
import java.io.*;
import java.lang.*;
import java.math.*;

class MinDifference {

	public static int minDifference(int[][] tuples) {
		int minDiff = Integer.MAX_VALUE;
		int min = 0;
		while (min < tuples.length) {
			int diff = Math.abs(tuples[min][0] - tuples[min][1]);
			if (minDiff > diff) {
				minDiff = diff;
			}
			min++;
		}
		return minDiff;
	}
}
