
import java.io.*;
import java.lang.*;
import java.math.*;

class FindMinLength {

	public static int findMinLength(int[][] arr) {
		int find = Integer.MAX_VALUE;
		for (int[] elements : arr) {
			if (elements.length < find) {
				find = elements.length;
			}
		}
		return find;
	}
}
