
import java.io.*;
import java.lang.*;
import java.math.*;

class GetEqual {

	public static String getEqual(int[][] input, int k) {
		int count = 0;
		for (int[] array : input) {
			if (array.length == k) {
				count += 1;
			}
		}
		if (count == input.length) {
			return "All tuples have same length";
		} else {
			return "All tuples do not have same length";
		}
	}
}
