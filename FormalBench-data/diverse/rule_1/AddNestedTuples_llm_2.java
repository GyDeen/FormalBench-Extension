
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
			for (int k = 0; k < cols; k++) {
				result[index][k] = array[index][k]
						+ test[index][k];
			}
		}
		return result;
	}
}
