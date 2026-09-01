
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class Array3d {

	public static String[][][] array3d(int m, int n, int o) {
		String[][][] arr3d = new String[o][n][m];

		int index = 0;
		while (index < o) {
			for (int j = 0; j < n; j++) {
				for (int k = 0; k < m; k++) {
					arr3d[index][j][k] = "*";
				}
			}
			index++;
		}

		return arr3d;
	}
}
