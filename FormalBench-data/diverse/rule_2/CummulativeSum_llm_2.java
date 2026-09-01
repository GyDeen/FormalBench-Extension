
import java.io.*;
import java.lang.*;
import java.math.*;

class CummulativeSum {

	public static int cummulativeSum(int[][] testArray) {
		int sum = 0;
		int result = 0;
		while (result < testArray.length) {
			for (int j = 0; j < testArray[result].length; j++) {
				sum += testArray[result][j];
			}
			result++;
		}
		return sum;
	}
}
