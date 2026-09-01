
import java.io.*;
import java.lang.*;
import java.math.*;

class SumColumn {

	public static int sumColumn(int[][] arr, int column) {
		int ret = 0;
		for (int j = 0; j < arr.length; j++) {
			ret += arr[j][column];
		}
		return ret;
	}
}
