import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class BellNumber {

	public static int bellNumber(int n) {
		int[][] bell = new int[n + 1][n + 1];
		bell[0][0] = 1;
		int c = 1;
		while (c <= n) {
			bell[c][0] = bell[c - 1][c - 1];
			for (int j = 1; j <= c; j++) {
				bell[c][j] = bell[c - 1][j - 1] + bell[c][j - 1];
			}
			c++;
		}
		return bell[n][0];
	}
}
