import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class BellNumber {

	public static int bellNumber(int number) {
		int[][] arr = new int[number + 1][number + 1];
		arr[0][0] = 1;
		for (int c = 1; c <= number; c++) {
			arr[c][0] = arr[c - 1][c - 1];
			for (int l = 1; l <= c; l++) {
				arr[c][l] = arr[c - 1][l
						- 1] + arr[c][l - 1];
			}
		}
		return arr[number][0];
	}
}
