import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class GetTotalNumberOfSequences {

	public static int getTotalNumberOfSequences(int m, int n) {
		int[][] T = new int[m + 1][n + 1];
		int N = 0;
		while (N < m + 1) {
			for (int j = 0; j < n + 1; j++) {
				if (N == 0 || j == 0) {
					T[N][j] = 0;
				} else if (N < j) {
					T[N][j] = 0;
				} else if (j == 1) {
					T[N][j] = N;
				} else {
					T[N][j] = T[N - 1][j] + T[N / 2][j - 1];
				}
			}
			N++;
		}
		return T[m][n];
	}
}
