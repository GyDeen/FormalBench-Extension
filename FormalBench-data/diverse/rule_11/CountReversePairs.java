
import java.io.*;
import java.lang.*;
import java.math.*;

class CountReversePairs {

	public static String countReversePairs(String[] testArray) {
		int count = 0, n = testArray.length;
		for (int i = 0; i < n; i++) {
			for (int j = i + 1; j < n; j++) {
				if (testArray[i].equals(reverse(testArray[j]))) {
					count++;
				}
			}
		}
		return Integer.toString(count);
	}

	private static String reverse(String str) {
		StringBuilder reversed = new StringBuilder(str);
		return reversed.reverse().toString();
	}
}
