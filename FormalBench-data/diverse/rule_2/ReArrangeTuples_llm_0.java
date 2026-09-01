
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ReArrangeTuples {

	public static int[][] reArrangeTuples(int[][] testArray, int[] ordArray) {
		int n = ordArray.length;
		int[][] res = new int[n][2];

		int index = 0;

		int num = 0;
		while (num < n) {
			for (int j = 0; j < testArray.length; j++) {
				if (testArray[j][0] == ordArray[num]) {
					res[index++] = testArray[j];
					break;
				}
			}
			num++;
		}

		return res;
	}
}
