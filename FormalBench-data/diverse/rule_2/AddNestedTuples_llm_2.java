
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class AddNestedTuples {

	public static int[][] addNestedTuples(int[][] testTup1, int[][] testTup2) {
		int rows = testTup1.length;
		int cols = testTup1[0].length;
		int[][] result = new int[rows][cols];

		int end = 0;
		while (end < rows) {
			for (int j = 0; j < cols; j++) {
				result[end][j] = testTup1[end][j] + testTup2[end][j];
			}
			end++;
		}
		return result;
	}
}
