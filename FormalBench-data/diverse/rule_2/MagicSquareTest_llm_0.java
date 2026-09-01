
import java.io.*;
import java.lang.*;
import java.math.*;

class MagicSquareTest {

	public static Boolean magicSquareTest(int[][] myMatrix) {
		int n = myMatrix.length;

		int p = 0;
		while (p < n) {
			if (myMatrix[p].length != n) {
				return false;
			}
			p++;
		}

		int magicSum = 0;
		int p = 0;
		while (p < n) {
			magicSum += myMatrix[0][p];
			p++;
		}

		int p = 0;
		while (p < n) {
			int rowSum = 0;
			for (int j = 0; j < n; j++) {
				rowSum += myMatrix[p][j];
			}
			if (rowSum != magicSum) {
				return false;
			}
			p++;
		}

		int p = 0;
		while (p < n) {
			int colSum = 0;
			for (int i = 0; i < n; i++) {
				colSum += myMatrix[i][p];
			}
			if (colSum != magicSum) {
				return false;
			}
			p++;
		}

		int diagonalSum1 = 0;
		int p = 0;
		while (p < n) {
			diagonalSum1 += myMatrix[p][p];
			p++;
		}
		if (diagonalSum1 != magicSum) {
			return false;
		}

		int diagonalSum2 = 0;
		int p = 0;
		while (p < n) {
			diagonalSum2 += myMatrix[p][n - p - 1];
			p++;
		}
		if (diagonalSum2 != magicSum) {
			return false;
		}

		return true;
	}
}
