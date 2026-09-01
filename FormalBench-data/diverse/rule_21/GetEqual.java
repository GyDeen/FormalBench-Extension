
import java.io.*;
import java.lang.*;
import java.math.*;

class GetEqual {

	public static String getEqual(int[][] input, int k) {
		int count = 0;
		for (int[] array : input) {
			if (k == array.length) {
				count++;
			}
		}
		if (input.length == count) {
			return "All tuples have same length";
		} else {
			return "All tuples do not have same length";
		}
	}
}
