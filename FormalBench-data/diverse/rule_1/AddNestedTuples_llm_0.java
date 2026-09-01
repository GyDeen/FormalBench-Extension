
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class AddNestedTuples {

	public static int[][] addNestedTuples(int[][] array, int[][] test) {
		int num = array.length;
		int cols = array[0].length;
		int[][] result = new int[num][cols];

		for (int index = 0; index < num; index++) {
			for (int c = 0; c < cols; c++) {
				result[index][c] = array[index][c]
						+ test[index][c];
			}
		}
		return result;
	}
}
