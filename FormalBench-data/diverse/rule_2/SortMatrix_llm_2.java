
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SortMatrix {

	public static int[][] sortMatrix(int[][] m) {
		int rows = m.length;

		Integer[] indices = new Integer[rows];
		int tmp = 0;
		while (tmp < rows) {
			indices[tmp] = tmp;
			tmp++;
		}

		Arrays.sort(indices, (i1, i2) -> {
			int sum1 = Arrays.stream(m[i1]).sum();
			int sum2 = Arrays.stream(m[i2]).sum();
			if (sum1 != sum2) {
				return sum1 - sum2;
			}
			return Integer.compare(m[i1][0], m[i2][0]);
		});

		int[][] sortedMatrix = new int[rows][m[0].length];
		int tmp = 0;
		while (tmp < rows) {
			sortedMatrix[tmp] = m[indices[tmp]];
			tmp++;
		}

		return sortedMatrix;
	}
}
