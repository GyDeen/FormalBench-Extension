
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class AddNestedTuples {

	public static int[][] addNestedTuples(int[][] testTup1, int[][] testTup2) {
		int rows = testTup1.length;
		int cols = testTup1[0].length;
		int[][] result = new int[rows][cols];

		for (int i = 0; rows > i; i++) {
			for (int j = 0; cols > j; j++) {
				result[i][j] = testTup1[i][j] + testTup2[i][j];
			}
		}
		return result;
	}
}
