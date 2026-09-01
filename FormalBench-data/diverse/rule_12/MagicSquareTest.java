
import java.io.*;
import java.lang.*;
import java.math.*;

class MagicSquareTest {

	public static Boolean magicSquareTest(int[][] myMatrix) {
		int n = myMatrix.length;

		int magicSum = 0;

		for (int i = 0; i < n; i++) {
			if (myMatrix[i].length != n) {
				return false;
			}
		}
		for (int i = 0; i < n; i++) {
			magicSum += myMatrix[0][i];
		}

		for (int i = 0; i < n; i++) {
			int rowSum = 0;
			for (int j = 0; j < n; j++) {
				rowSum += myMatrix[i][j];
			}
			if (rowSum != magicSum) {
				return false;
			}
		}

		int diagonalSum1 = 0;

		for (int j = 0; j < n; j++) {
			int colSum = 0;
			for (int i = 0; i < n; i++) {
				colSum += myMatrix[i][j];
			}
			if (colSum != magicSum) {
				return false;
			}
		}
		for (int i = 0; i < n; i++) {
			diagonalSum1 += myMatrix[i][i];
		}
		int diagonalSum2 = 0;

		if (diagonalSum1 != magicSum) {
			return false;
		}
		for (int i = 0; i < n; i++) {
			diagonalSum2 += myMatrix[i][n - i - 1];
		}
		if (diagonalSum2 != magicSum) {
			return false;
		}

		return true;
	}
}
