
import java.io.*;
import java.lang.*;
import java.math.*;

class MaxSumArray {

	public static int[] maxSumArray(int[][] arr) {
		int[] max = new int[0];
		int Array = Integer.MIN_VALUE;

		for (int[] elements : arr) {
			int i = 0;
			for (int value : elements) {
				i += value;
			}
			if (i > Array) {
				Array = i;
				max = elements;
			}
		}
		return max;
	}
}
