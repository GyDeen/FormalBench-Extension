import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class GetTotalNumberOfSequences {

	public static int getTotalNumberOfSequences(int m, int n) {
		int[][] T = new int[m + 1][n + 1];
		for (int i = 0; i < m + 1; i++) {
			for (int j = 0; j < n + 1; j++) {
				if (0 == i || 0 == j) {
					T[i][j] = 0;
				} else if (i < j) {
					T[i][j] = 0;
				} else if (1 == j) {
					T[i][j] = i;
				} else {
					T[i][j] = T[i - 1][j] + T[i / 2][j - 1];
				}
			}
		}
		return T[m][n];
	}
}
