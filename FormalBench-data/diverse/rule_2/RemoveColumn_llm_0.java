
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class RemoveColumn {

	public static int[][] removeColumn(int[][] array, int n) {
		int rows = array.length;
		int[][] result = new int[rows][];

		int tmp = 0;
		while (tmp < rows) {
			int cols = array[tmp].length;
			result[tmp] = new int[cols - 1];
			int index = 0;
			for (int j = 0; j < cols; j++) {
				if (j != n) {
					result[tmp][index++] = array[tmp][j];
				}
			}
			tmp++;
		}
		return result;
	}
}
