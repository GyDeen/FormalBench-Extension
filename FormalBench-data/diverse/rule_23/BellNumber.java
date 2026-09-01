import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class BellNumber {

	public static int bellNumber(int n) {
		int[][] bell = new int[n + 1][n + 1];
		bell[0][0] = 1;
		for (int i = 1; n >= i; i++) {
			bell[i][0] = bell[i - 1][i - 1];
			for (int j = 1; i >= j; j++) {
				bell[i][j] = bell[i - 1][j - 1] + bell[i][j - 1];
			}
		}
		return bell[n][0];
	}
}
