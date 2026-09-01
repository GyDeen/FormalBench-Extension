
import java.io.*;
import java.lang.*;
import java.util.*;

class ExtractColumn {

	public static int[] extractColumn(int[][] array, int n) {
		int rows = array.length;
		int[] result = new int[rows];

		int col = 0;
		while (col < rows) {
			result[col] = array[col][n];
			col++;
		}
		return result;
	}
}
