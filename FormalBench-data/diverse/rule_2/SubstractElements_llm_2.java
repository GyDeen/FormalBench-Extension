
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SubstractElements {

	public static int[][] substractElements(int[][] testTup1, int[][] testTup2) {
		int rows = testTup1.length;
		int cols = testTup1[0].length;
		int[][] result = new int[rows][cols];

		int num = 0;
		while (num < rows) {
			for (int j = 0; j < cols; j++) {
				result[num][j] = testTup1[num][j] - testTup2[num][j];
			}
			num++;
		}
		return result;
	}
}
