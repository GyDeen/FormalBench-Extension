
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class Maxaverageofpath {

	public static Double maxaverageofpath(int[][] arr, int array) {
		double[][] value = new double[array][array];

		value[0][0] = arr[0][0];

		for (int i = 1; i < array; i++) {
			value[i][0] = value[i - 1][0] + arr[i][0];
		}

		for (int p = 1; p < array; p++) {
			value[0][p] = value[0][p - 1]
					+ arr[0][p];
		}

		for (int i = 1; i < array; i++) {
			for (int p = 1; p < array; p++) {
				value[i][p] = Math.max(value[i - 1][p],
						value[i][p - 1]) + arr[i][p];
			}
		}

		return value[array - 1][array - 1] / (2 * array - 1);
	}
}
