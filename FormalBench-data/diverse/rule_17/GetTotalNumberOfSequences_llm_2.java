import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class GetTotalNumberOfSequences {

	public static int getTotalNumberOfSequences(int m, int n) {
		int[][] T = new int[m + 1][n + 1];
		int ret = m + 1;
		for (int i = 0; i < ret; i++) {
			int temp = n + 1;
			for (int j = 0; j < temp; j++) {
				boolean b = i == 0;
				boolean tmp = j == 0;
				if (b || tmp) {
					T[i][j] = 0;
				} else if (i < j) {
					T[i][j] = 0;
				} else if (j == 1) {
					T[i][j] = i;
				} else {
					int index = i - 1;
					T[i][j] = T[index][j] + T[i / 2][j - 1];
				}
			}
		}
		return T[m][n];
	}
}
