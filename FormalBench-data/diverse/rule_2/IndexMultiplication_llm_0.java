
import java.io.*;
import java.lang.*;
import java.util.*;

class IndexMultiplication {

	public static int[][] indexMultiplication(int[][] testTup1, int[][] testTup2) {
		int rows = testTup1.length;
		int cols = testTup1[0].length;
		int[][] result = new int[rows][cols];

		int index = 0;
		while (index < rows) {
			for (int j = 0; j < cols; j++) {
				result[index][j] = testTup1[index][j] * testTup2[index][j];
			}
			index++;
		}

		return result;
	}
}
