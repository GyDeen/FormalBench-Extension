
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class Array3d {

	public static String[][][] array3d(int array, int n, int p) {
		String[][][] arr3d = new String[p][n][array];

		for (int c = 0; c < p; c++) {
			for (int r = 0; r < n; r++) {
				for (int a = 0; a < array; a++) {
					arr3d[c][r][a] = "*";
				}
			}
		}

		return arr3d;
	}
}
