
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class Array3d {

	public static String[][][] array3d(int array, int n, int d) {
		String[][][] arr3d = new String[d][n][array];

		for (int c = 0; c < d; c++) {
			for (int r = 0; r < n; r++) {
				for (int p = 0; p < array; p++) {
					arr3d[c][r][p] = "*";
				}
			}
		}

		return arr3d;
	}
}
