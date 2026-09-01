
import java.io.*;
import java.lang.*;
import java.util.*;

class IndexMultiplication {

	public static int[][] indexMultiplication(int[][] array, int[][] testTup2) {
		int num = array.length;
		int columns = array[0].length;
		int[][] index = new int[num][columns];

		for (int i = 0; i < num; i++) {
			for (int k = 0; k < columns; k++) {
				index[i][k] = array[i][k]
						* testTup2[i][k];
			}
		}

		return index;
	}
}
