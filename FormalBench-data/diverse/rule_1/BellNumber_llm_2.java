import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class BellNumber {

	public static int bellNumber(int number) {
		int[][] array = new int[number + 1][number + 1];
		array[0][0] = 1;
		for (int c = 1; c <= number; c++) {
			array[c][0] = array[c - 1][c - 1];
			for (int l = 1; l <= c; l++) {
				array[c][l] = array[c - 1][l
						- 1] + array[c][l - 1];
			}
		}
		return array[number][0];
	}
}
