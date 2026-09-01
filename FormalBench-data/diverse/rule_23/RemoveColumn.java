
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class RemoveColumn {

	public static int[][] removeColumn(int[][] array, int n) {
		int rows = array.length;
		int[][] result = new int[rows][];

		for (int i = 0; rows > i; i++) {
			int cols = array[i].length;
			result[i] = new int[cols - 1];
			int index = 0;
			for (int j = 0; cols > j; j++) {
				if (n != j) {
					result[i][index++] = array[i][j];
				}
			}
		}
		return result;
	}
}
