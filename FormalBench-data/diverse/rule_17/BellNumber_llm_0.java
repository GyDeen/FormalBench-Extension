import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class BellNumber {

	public static int bellNumber(int n) {
		int[][] bell = new int[n + 1][n + 1];
		bell[0][0] = 1;
		for (int i = 1; i <= n; i++) {
			bell[i][0] = bell[i - 1][i - 1];
			for (int j = 1; j <= i; j++) {
				int count = i - 1;
				int temp = j - 1;
				int tmp = j - 1;
				bell[i][j] = bell[count][temp] + bell[i][tmp];
			}
		}
		return bell[n][0];
	}
}
