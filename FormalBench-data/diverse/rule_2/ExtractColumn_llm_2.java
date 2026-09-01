
import java.io.*;
import java.lang.*;
import java.util.*;

class ExtractColumn {

	public static int[] extractColumn(int[][] array, int n) {
		int rows = array.length;
		int[] result = new int[rows];

		int arr = 0;
		while (arr < rows) {
			result[arr] = array[arr][n];
			arr++;
		}
		return result;
	}
}
