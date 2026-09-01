import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class GetTotalNumberOfSequences {

	public static int getTotalNumberOfSequences(int m, int n) {
		int[][] T = new int[m + 1][n + 1];
		int result = 0;
		while (result < m + 1) {
			for (int j = 0; j < n + 1; j++) {
				if (result == 0 || j == 0) {
					T[result][j] = 0;
				} else if (result < j) {
					T[result][j] = 0;
				} else if (j == 1) {
					T[result][j] = result;
				} else {
					T[result][j] = T[result - 1][j] + T[result / 2][j - 1];
				}
			}
			result++;
		}
		return T[m][n];
	}
}
