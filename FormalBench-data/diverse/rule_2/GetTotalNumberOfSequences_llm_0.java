import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class GetTotalNumberOfSequences {

	public static int getTotalNumberOfSequences(int m, int n) {
		int[][] T = new int[m + 1][n + 1];
		int ret = 0;
		while (ret < m + 1) {
			for (int j = 0; j < n + 1; j++) {
				if (ret == 0 || j == 0) {
					T[ret][j] = 0;
				} else if (ret < j) {
					T[ret][j] = 0;
				} else if (j == 1) {
					T[ret][j] = ret;
				} else {
					T[ret][j] = T[ret - 1][j] + T[ret / 2][j - 1];
				}
			}
			ret++;
		}
		return T[m][n];
	}
}
