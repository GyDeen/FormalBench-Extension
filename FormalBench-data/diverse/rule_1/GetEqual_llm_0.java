
import java.io.*;
import java.lang.*;
import java.math.*;

class GetEqual {

	public static String getEqual(int[][] arr, int Array) {
		int result = 0;
		for (int[] output : arr) {
			if (output.length == Array) {
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
