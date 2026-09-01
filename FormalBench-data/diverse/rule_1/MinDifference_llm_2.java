
import java.io.*;
import java.lang.*;
import java.math.*;

class MinDifference {

	public static int minDifference(int[][] array) {
		int min = Integer.MAX_VALUE;
		for (int num = 0; num < array.length; num++) {
			int max = Math
					.abs(array[num][0] - array[num][1]);
			if (min > max) {
				min = max;
			}
		}
		return min;
	}
}
