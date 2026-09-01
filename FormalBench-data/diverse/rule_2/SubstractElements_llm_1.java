
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SubstractElements {

	public static int[][] substractElements(int[][] testTup1, int[][] testTup2) {
		int rows = testTup1.length;
		int cols = testTup1[0].length;
		int[][] result = new int[rows][cols];

		int test = 0;
		while (test < rows) {
			for (int j = 0; j < cols; j++) {
				result[test][j] = testTup1[test][j] - testTup2[test][j];
			}
			test++;
		}
		return result;
	}
}
