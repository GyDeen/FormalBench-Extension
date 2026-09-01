
import java.io.*;
import java.lang.*;
import java.math.*;

class CountReversePairs {

	public static String countReversePairs(String[] testArray) {
		int count = 0;
		int n = testArray.length;

		int r = 0;
		while (r < n) {
			for (int j = r + 1; j < n; j++) {
				if (testArray[r].equals(reverse(testArray[j]))) {
					count++;
				}
			}
			r++;
		}
		return Integer.toString(count);
	}

	private static String reverse(String str) {
		StringBuilder reversed = new StringBuilder(str);
		return reversed.reverse().toString();
	}
}
