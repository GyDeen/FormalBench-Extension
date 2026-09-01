
import java.io.*;
import java.lang.*;
import java.util.*;

class ExtractColumn {

	public static int[] extractColumn(int[][] arr, int column) {
		int Array = arr.length;
		int[] columns = new int[Array];

		for (int index = 0; index < Array; index++) {
			columns[index] = arr[index][column];
		}
		return columns;
	}
}
