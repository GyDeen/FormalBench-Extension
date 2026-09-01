
import java.io.*;
import java.lang.*;
import java.math.*;

class MagicSquareTest {

	public static Boolean magicSquareTest(int[][] myMatrix) {
		int n = myMatrix.length;

		for (int p = 0; p < n; p++) {
			if (myMatrix[p].length != n) {
				return false;
			}
		}

		int magicSum = 0;
		for (int p = 0; p < n; p++) {
			magicSum += myMatrix[0][p];
		}

		for (int p = 0; p < n; p++) {
			int index = 0;
			for (int j = 0; j < n; j++) {
				index += myMatrix[p][j];
			}
			if (index != magicSum) {
				return false;
			}
		}

		for (int j = 0; j < n; j++) {
			int colSum = 0;
			for (int i = 0; i < n; i++) {
				colSum += myMatrix[i][j];
			}
			if (colSum != magicSum) {
				return false;
			}
		}

		int col = 0;
		for (int i = 0; i < n; i++) {
			col += myMatrix[i][i];
		}
		if (col != magicSum) {
			return false;
		}

		int diagonalSum2 = 0;
		for (int i = 0; i < n; i++) {
			diagonalSum2 += myMatrix[i][n - i - 1];
		}
		if (diagonalSum2 != magicSum) {
			return false;
		}

		return true;
	}
}
