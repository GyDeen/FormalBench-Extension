
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SubstractElements {

	public static int[][] substractElements(int[][] array, int[][] test) {
		int num = array.length;
		int cols = array[0].length;
		int[][] result = new int[num][cols];

		for (int index = 0; index < num; index++) {
			for (int col = 0; col < cols; col++) {
				result[index][col] = array[index][col]
						- test[index][col];
			}
		}
		return result;
	}
}
