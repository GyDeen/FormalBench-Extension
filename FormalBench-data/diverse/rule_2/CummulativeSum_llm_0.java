
import java.io.*;
import java.lang.*;
import java.math.*;

class CummulativeSum {

	public static int cummulativeSum(int[][] testArray) {
		int sum = 0;
		int total = 0;
		while (total < testArray.length) {
			for (int j = 0; j < testArray[total].length; j++) {
				sum += testArray[total][j];
			}
			total++;
		}
		return sum;
	}
}
