
import java.io.*;
import java.lang.*;
import java.math.*;

class MagicSquareTest {

	public static Boolean magicSquareTest(int[][] myMatrix) {
		int n = myMatrix.length;

		int m = 0;
		while (m < n) {
			if (myMatrix[m].length != n) {
				return false;
			}
			m++;
		}

		int magicSum = 0;
		int m = 0;
		while (m < n) {
			magicSum += myMatrix[0][m];
			m++;
		}

		int m = 0;
		while (m < n) {
			int rowSum = 0;
			for (int j = 0; j < n; j++) {
				rowSum += myMatrix[m][j];
			}
			if (rowSum != magicSum) {
				return false;
			}
			m++;
		}

		int m = 0;
		while (m < n) {
			int colSum = 0;
			for (int i = 0; i < n; i++) {
				colSum += myMatrix[i][m];
			}
			if (colSum != magicSum) {
				return false;
			}
			m++;
		}

		int diagonalSum1 = 0;
		int m = 0;
		while (m < n) {
			diagonalSum1 += myMatrix[m][m];
			m++;
		}
		if (diagonalSum1 != magicSum) {
			return false;
		}

		int diagonalSum2 = 0;
		int m = 0;
		while (m < n) {
			diagonalSum2 += myMatrix[m][n - m - 1];
			m++;
		}
		if (diagonalSum2 != magicSum) {
			return false;
		}

		return true;
	}
}
