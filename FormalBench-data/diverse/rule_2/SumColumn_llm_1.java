
import java.io.*;
import java.lang.*;
import java.math.*;

class SumColumn {

	public static int sumColumn(int[][] array, int c) {
		int sum = 0;
		int count = 0;
		while (count < array.length) {
			sum += array[count][c];
			count++;
		}
		return sum;
	}
}
