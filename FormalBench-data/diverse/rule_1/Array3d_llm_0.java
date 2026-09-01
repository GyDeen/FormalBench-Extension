
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class Array3d {

	public static String[][][] array3d(int array, int n, int p) {
		String[][][] arr3d = new String[p][n][array];

		for (int index = 0; index < p; index++) {
			for (int r = 0; r < n; r++) {
				for (int c = 0; c < array; c++) {
					arr3d[index][r][c] = "*";
				}
			}
		}

		return arr3d;
	}
}
