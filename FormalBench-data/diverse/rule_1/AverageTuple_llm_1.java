
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class AverageTuple {

	public static double[] averageTuple(int[][] array) {
		int rows = array.length;
		int columns = array[0].length;
		double[] avg = new double[columns];

		for (int n = 0; n < columns; n++) {
			double value = 0;
			for (int j = 0; j < rows; j++) {
				value += array[j][n];
			}
			avg[n] = value / rows;
		}
		return avg;
	}
}
