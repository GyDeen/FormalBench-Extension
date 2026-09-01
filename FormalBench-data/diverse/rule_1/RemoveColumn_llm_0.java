
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class RemoveColumn {

	public static int[][] removeColumn(int[][] column, int col) {
		int num = column.length;
		int[][] columns = new int[num][];

		for (int i = 0; i < num; i++) {
			int cols = column[i].length;
			columns[i] = new int[cols - 1];
			int row = 0;
			for (int j = 0; j < cols; j++) {
				if (j != col) {
					columns[i][row++] = column[i][j];
				}
			}
		}
		return columns;
	}
}
