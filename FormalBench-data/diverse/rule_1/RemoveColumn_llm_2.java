
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class RemoveColumn {

	public static int[][] removeColumn(int[][] arr, int col) {
		int num = arr.length;
		int[][] column = new int[num][];

		for (int i = 0; i < num; i++) {
			int cols = arr[i].length;
			column[i] = new int[cols - 1];
			int row = 0;
			for (int j = 0; j < cols; j++) {
				if (j != col) {
					column[i][row++] = arr[i][j];
				}
			}
		}
		return column;
	}
}
