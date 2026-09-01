
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class Maxaverageofpath {

	public static Double maxaverageofpath(int[][] array, int num) {
		double[][] weight = new double[num][num];

		weight[0][0] = array[0][0];

		for (int i = 1; i < num; i++) {
			weight[i][0] = weight[i - 1][0] + array[i][0];
		}

		for (int p = 1; p < num; p++) {
			weight[0][p] = weight[0][p - 1]
					+ array[0][p];
		}

		for (int i = 1; i < num; i++) {
			for (int p = 1; p < num; p++) {
				weight[i][p] = Math.max(weight[i - 1][p],
						weight[i][p - 1]) + array[i][p];
			}
		}

		return weight[num - 1][num - 1] / (2 * num - 1);
	}
}
