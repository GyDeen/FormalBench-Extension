
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class Maxaverageofpath {

	public static Double maxaverageofpath(int[][] array, int num) {
		double[][] value = new double[num][num];

		value[0][0] = array[0][0];

		for (int i = 1; i < num; i++) {
			value[i][0] = value[i - 1][0] + array[i][0];
		}

		for (int p = 1; p < num; p++) {
			value[0][p] = value[0][p - 1]
					+ array[0][p];
		}

		for (int i = 1; i < num; i++) {
			for (int p = 1; p < num; p++) {
				value[i][p] = Math.max(value[i - 1][p],
						value[i][p - 1]) + array[i][p];
			}
		}

		return value[num - 1][num - 1] / (2 * num - 1);
	}
}
