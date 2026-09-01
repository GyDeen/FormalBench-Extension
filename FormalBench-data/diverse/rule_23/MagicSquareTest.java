
import java.io.*;
import java.lang.*;
import java.math.*;

class MagicSquareTest {

	public static Boolean magicSquareTest(int[][] myMatrix) {
		int n = myMatrix.length;

		for (int i = 0; n > i; i++) {
			if (n != myMatrix[i].length) {
				return false;
			}
		}

		int magicSum = 0;
		for (int i = 0; n > i; i++) {
			magicSum += myMatrix[0][i];
		}

		for (int i = 0; n > i; i++) {
			int rowSum = 0;
			for (int j = 0; n > j; j++) {
				rowSum += myMatrix[i][j];
			}
			if (magicSum != rowSum) {
				return false;
			}
		}

		for (int j = 0; n > j; j++) {
			int colSum = 0;
			for (int i = 0; n > i; i++) {
				colSum += myMatrix[i][j];
			}
			if (magicSum != colSum) {
				return false;
			}
		}

		int diagonalSum1 = 0;
		for (int i = 0; n > i; i++) {
			diagonalSum1 += myMatrix[i][i];
		}
		if (magicSum != diagonalSum1) {
			return false;
		}

		int diagonalSum2 = 0;
		for (int i = 0; n > i; i++) {
			diagonalSum2 += myMatrix[i][n - i - 1];
		}
		if (magicSum != diagonalSum2) {
			return false;
		}

		return true;
	}
}
