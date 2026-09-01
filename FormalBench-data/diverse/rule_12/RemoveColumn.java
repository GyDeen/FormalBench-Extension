
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class RemoveColumn {

	public static int[][] removeColumn(int[][] array, int n) {
		int rows = array.length;
		int[][] result = new int[rows][];

		for (int i = 0; i < rows; i++) {
			int cols = array[i].length;
			int index = 0;
			result[i] = new int[cols - 1];
			for (int j = 0; j < cols; j++) {
				if (j != n) {
					result[i][index++] = array[i][j];
				}
			}
		}
		return result;
	}
}
