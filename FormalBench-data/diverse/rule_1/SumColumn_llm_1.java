
import java.io.*;
import java.lang.*;
import java.math.*;

class SumColumn {

	public static int sumColumn(int[][] arr, int column) {
		int result = 0;
		for (int index = 0; index < arr.length; index++) {
			result += arr[index][column];
		}
		return result;
	}
}
