
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class Array3d {

	public static String[][][] array3d(int m, int n, int o) {
		String[][][] arr3d = new String[o][n][m];

		int len = 0;
		while (len < o) {
			for (int j = 0; j < n; j++) {
				for (int k = 0; k < m; k++) {
					arr3d[len][j][k] = "*";
				}
			}
			len++;
		}

		return arr3d;
	}
}
