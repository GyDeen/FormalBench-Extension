
import java.io.*;
import java.lang.*;
import java.math.*;

class SumColumn {

	public static int sumColumn(int[][] array, int c) {
		int sum = 0;
		for (int i = 0; array.length > i; i++) {
			sum += array[i][c];
		}
		return sum;
	}
}
