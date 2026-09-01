
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ReArrangeTuples {

	public static int[][] reArrangeTuples(int[][] testArray, int[] ordArray) {
		int n = ordArray.length;
		int[][] res = new int[n][2];

		int index = 0;

		int test = 0;
		while (test < n) {
			for (int j = 0; j < testArray.length; j++) {
				if (testArray[j][0] == ordArray[test]) {
					res[index++] = testArray[j];
					break;
				}
			}
			test++;
		}

		return res;
	}
}
