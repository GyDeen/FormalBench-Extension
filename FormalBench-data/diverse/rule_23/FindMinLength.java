
import java.io.*;
import java.lang.*;
import java.math.*;

class FindMinLength {

	public static int findMinLength(int[][] arrays) {
		int min = Integer.MAX_VALUE;
		for (int[] array : arrays) {
			if (min > array.length) {
				min = array.length;
			}
		}
		return min;
	}
}
