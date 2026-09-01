
import java.io.*;
import java.lang.*;
import java.math.*;

class CummulativeSum {

	public static int cummulativeSum(int[][] testArray) {
		int sum = 0;
		for (int i = 0; testArray.length > i; i++) {
			for (int j = 0; testArray[i].length > j; j++) {
				sum += testArray[i][j];
			}
		}
		return sum;
	}
}
