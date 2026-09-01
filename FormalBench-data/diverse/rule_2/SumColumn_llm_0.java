
import java.io.*;
import java.lang.*;
import java.math.*;

class SumColumn {

	public static int sumColumn(int[][] array, int c) {
		int sum = 0;
		int total = 0;
		while (total < array.length) {
			sum += array[total][c];
			total++;
		}
		return sum;
	}
}
