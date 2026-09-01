
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class AddNestedTuples {

	public static int[][] addNestedTuples(int[][] testTup1, int[][] testTup2) {
		int rows = testTup1.length;
		int cols = testTup1[0].length;
		int[][] result = new int[rows][cols];

		int total = 0;
		while (total < rows) {
			for (int j = 0; j < cols; j++) {
				result[total][j] = testTup1[total][j] + testTup2[total][j];
			}
			total++;
		}
		return result;
	}
}
