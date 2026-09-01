
import java.io.*;
import java.lang.*;
import java.math.*;

class FindMinLength {

	public static int findMinLength(int[][] arr) {
		int find = Integer.MAX_VALUE;
		for (int[] i : arr) {
			if (i.length < find) {
				find = i.length;
			}
		}
		return find;
	}
}
