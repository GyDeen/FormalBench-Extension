
import java.io.*;
import java.lang.*;
import java.util.*;

class IndexMultiplication {

	public static int[][] indexMultiplication(int[][] testTup1, int[][] testTup2) {
		int rows = testTup1.length, cols = testTup1[0].length;
		int[][] result = new int[rows][cols];

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				result[i][j] = testTup1[i][j] * testTup2[i][j];
			}
		}

		return result;
	}
}
