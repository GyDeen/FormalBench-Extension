
import java.io.*;
import java.lang.*;
import java.math.*;

class CountReversePairs {

	public static String countReversePairs(String[] testArray) {
		int ret = 0;
		int n = testArray.length;

		for (int p = 0; p < n; p++) {
			for (int c = p + 1; c < n; c++) {
				if (testArray[p].equals(reverse(testArray[c]))) {
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
