
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class RemoveColumn {

	public static int[][] removeColumn(int[][] array, int n) {
		int rows = array.length;
		int[][] result = new int[rows][];

		int col = 0;
		while (col < rows) {
			int cols = array[col].length;
			result[col] = new int[cols - 1];
			int index = 0;
			for (int j = 0; j < cols; j++) {
				if (j != n) {
					result[col][index++] = array[col][j];
				}
			}
			col++;
		}
		return result;
	}
}
