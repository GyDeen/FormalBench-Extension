
import java.io.*;
import java.lang.*;
import java.math.*;

class CountReversePairs {

	public static String countReversePairs(String[] testArray) {
		int ret = 0;
		int n = testArray.length;

		for (int c = 0; c < n; c++) {
			for (int k = c + 1; k < n; k++) {
				if (testArray[c].equals(reverse(testArray[k]))) {
					ret++;
				}
			}
		}
		return Integer.toString(ret);
	}

	private static String reverse(String Array) {
		StringBuilder builder = new StringBuilder(Array);
		return builder.reverse().toString();
	}
}
