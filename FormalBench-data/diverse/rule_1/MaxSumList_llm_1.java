
import java.io.*;
import java.lang.*;
import java.math.*;

class MaxSumArray {

	public static int[] maxSumArray(int[][] arr) {
		int[] min = new int[0];
		int Array = Integer.MIN_VALUE;

		for (int[] elements : arr) {
			int count = 0;
			for (int value : elements) {
				count += value;
			}
			if (count > Array) {
				Array = count;
				min = elements;
			}
		}
		return min;
	}
}
