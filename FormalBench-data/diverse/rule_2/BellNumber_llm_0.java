import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class BellNumber {

	public static int bellNumber(int n) {
		int[][] bell = new int[n + 1][n + 1];
		bell[0][0] = 1;
		int count = 1;
		while (count <= n) {
			bell[count][0] = bell[count - 1][count - 1];
			for (int j = 1; j <= count; j++) {
				bell[count][j] = bell[count - 1][j - 1] + bell[count][j - 1];
			}
			count++;
		}
		return bell[n][0];
	}
}
