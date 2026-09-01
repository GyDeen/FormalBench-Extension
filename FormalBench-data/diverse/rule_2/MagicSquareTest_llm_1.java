
import java.io.*;
import java.lang.*;
import java.math.*;

class MagicSquareTest {

	public static Boolean magicSquareTest(int[][] myMatrix) {
		int n = myMatrix.length;

		int r = 0;
		while (r < n) {
			if (myMatrix[r].length != n) {
				return false;
			}
			r++;
		}

		int magicSum = 0;
		int r = 0;
		while (r < n) {
			magicSum += myMatrix[0][r];
			r++;
		}

		int r = 0;
		while (r < n) {
			int rowSum = 0;
			for (int j = 0; j < n; j++) {
				rowSum += myMatrix[r][j];
			}
			if (rowSum != magicSum) {
				return false;
			}
			r++;
		}

		int r = 0;
		while (r < n) {
			int colSum = 0;
			for (int i = 0; i < n; i++) {
				colSum += myMatrix[i][r];
			}
			if (colSum != magicSum) {
				return false;
			}
			r++;
		}

		int diagonalSum1 = 0;
		int r = 0;
		while (r < n) {
			diagonalSum1 += myMatrix[r][r];
			r++;
		}
		if (diagonalSum1 != magicSum) {
			return false;
		}

		int diagonalSum2 = 0;
		int r = 0;
		while (r < n) {
			diagonalSum2 += myMatrix[r][n - r - 1];
			r++;
		}
		if (diagonalSum2 != magicSum) {
			return false;
		}

		return true;
	}
}
