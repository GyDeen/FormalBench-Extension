import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class BellNumber {

	public static int bellNumber(int n) {
		int[][] bell = new int[n + 1][n + 1];
		bell[0][0] = 1;
		int b = 1;
		while (b <= n) {
			bell[b][0] = bell[b - 1][b - 1];
			for (int j = 1; j <= b; j++) {
				bell[b][j] = bell[b - 1][j - 1] + bell[b][j - 1];
			}
			b++;
		}
		return bell[n][0];
	}
}
