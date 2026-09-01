
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class Array3d {

	public static String[][][] array3d(int m, int n, int o) {
		String[][][] arr3d = new String[o][n][m];

		for (int i = 0; o > i; i++) {
			for (int j = 0; n > j; j++) {
				for (int k = 0; m > k; k++) {
					arr3d[i][j][k] = "*";
				}
			}
		}

		return arr3d;
	}
}
