
import java.io.*;
import java.lang.*;
import java.util.*;

class IndexMultiplication {

	public static int[][] indexMultiplication(int[][] arr, int[][] testTup2) {
		int num = arr.length;
		int columns = arr[0].length;
		int[][] index = new int[num][columns];

		for (int i = 0; i < num; i++) {
			for (int col = 0; col < columns; col++) {
				index[i][col] = arr[i][col]
						* testTup2[i][col];
			}
		}

		return index;
	}
}
