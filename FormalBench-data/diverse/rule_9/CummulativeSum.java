
import java.io.*;
import java.lang.*;
import java.math.*;

class CummulativeSum {

	public static int cummulativeSum(int[][] testArray) {
		int sum = 0;
		for (int i = 0; i < testArray.length; i++) {
			for (int j = 0; j < testArray[i].length; j++) {
				sum = sum + (testArray[i][j]);
			}
		}
		return sum;
	}
}
