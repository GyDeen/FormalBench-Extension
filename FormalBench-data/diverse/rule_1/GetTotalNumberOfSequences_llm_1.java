import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class GetTotalNumberOfSequences {

	public static int getTotalNumberOfSequences(int num, int b) {
		int[][] seq = new int[num + 1][b + 1];
		for (int index = 0; index < num + 1; index++) {
			for (int c = 0; c < b + 1; c++) {
				if (index == 0 || c == 0) {
					seq[index][c] = 0;
				} else if (index < c) {
					seq[index][c] = 0;
				} else if (c == 1) {
					seq[index][c] = index;
				} else {
					seq[index][c] = seq[index - 1][c]
							+ seq[index / 2][c - 1];
				}
			}
		}
		return seq[num][b];
	}
}
