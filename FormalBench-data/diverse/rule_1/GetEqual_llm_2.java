
import java.io.*;
import java.lang.*;
import java.math.*;

class GetEqual {

	public static String getEqual(int[][] arr, int num) {
		int result = 0;
		for (int[] output : arr) {
			if (output.length == num) {
				result++;
			}
		}
		if (result == arr.length) {
			return "All tuples have same length";
		} else {
			return "All tuples do not have same length";
		}
	}
}
