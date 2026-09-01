
import java.io.*;
import java.lang.*;
import java.math.*;

class FindMinLength {

	public static int findMinLength(int[][] args) {
		int find = Integer.MAX_VALUE;
		for (int[] elements : args) {
			if (elements.length < find) {
				find = elements.length;
			}
		}
		return find;
	}
}
