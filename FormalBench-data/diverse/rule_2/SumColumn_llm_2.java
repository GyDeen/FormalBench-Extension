
import java.io.*;
import java.lang.*;
import java.math.*;

class SumColumn {

	public static int sumColumn(int[][] array, int c) {
		int sum = 0;
		int max = 0;
		while (max < array.length) {
			sum += array[max][c];
			max++;
		}
		return sum;
	}
}
