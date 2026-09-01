
import java.io.*;
import java.lang.*;
import java.util.*;

class ExtractColumn {

	public static int[] extractColumn(int[][] array, int n) {
		int rows = array.length;
		int[] result = new int[rows];

		for (int i = 0; rows > i; i++) {
			result[i] = array[i][n];
		}
		return result;
	}
}
