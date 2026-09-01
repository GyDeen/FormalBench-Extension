
import java.io.*;
import java.lang.*;
import java.math.*;

class SumColumn {

	public static int sumColumn(int[][] arr, int column) {
		int ret = 0;
		for (int index = 0; index < arr.length; index++) {
			ret += arr[index][column];
		}
		return ret;
	}
}
