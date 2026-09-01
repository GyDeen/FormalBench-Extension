import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class GetTotalNumberOfSequences {

	public static int getTotalNumberOfSequences(int num, int c) {
		int[][] seq = new int[num + 1][c + 1];
		for (int index = 0; index < num + 1; index++) {
			for (int k = 0; k < c + 1; k++) {
				if (index == 0 || k == 0) {
					seq[index][k] = 0;
				} else if (index < k) {
					seq[index][k] = 0;
				} else if (k == 1) {
					seq[index][k] = index;
				} else {
					seq[index][k] = seq[index - 1][k]
							+ seq[index / 2][k - 1];
				}
			}
		}
		return seq[num][c];
	}
}
