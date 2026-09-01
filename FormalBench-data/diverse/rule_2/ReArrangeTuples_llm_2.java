
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ReArrangeTuples {

	public static int[][] reArrangeTuples(int[][] testArray, int[] ordArray) {
		int n = ordArray.length;
		int[][] res = new int[n][2];

		int index = 0;

		int count = 0;
		while (count < n) {
			for (int j = 0; j < testArray.length; j++) {
				if (testArray[j][0] == ordArray[count]) {
					res[index++] = testArray[j];
					break;
				}
			}
			count++;
		}

		return res;
	}
}
