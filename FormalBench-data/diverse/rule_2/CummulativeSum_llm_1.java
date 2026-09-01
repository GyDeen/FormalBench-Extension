
import java.io.*;
import java.lang.*;
import java.math.*;

class CummulativeSum {

	public static int cummulativeSum(int[][] testArray) {
		int sum = 0;
		int count = 0;
		while (count < testArray.length) {
			for (int j = 0; j < testArray[count].length; j++) {
				sum += testArray[count][j];
			}
			count++;
		}
		return sum;
	}
}
