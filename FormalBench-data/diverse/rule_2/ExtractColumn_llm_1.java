
import java.io.*;
import java.lang.*;
import java.util.*;

class ExtractColumn {

	public static int[] extractColumn(int[][] array, int n) {
		int rows = array.length;
		int[] result = new int[rows];

		int tmp = 0;
		while (tmp < rows) {
			result[tmp] = array[tmp][n];
			tmp++;
		}
		return result;
	}
}
