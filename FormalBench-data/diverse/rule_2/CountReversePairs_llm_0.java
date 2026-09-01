
import java.io.*;
import java.lang.*;
import java.math.*;

class CountReversePairs {

	public static String countReversePairs(String[] testArray) {
		int count = 0;
		int n = testArray.length;

		int total = 0;
		while (total < n) {
			for (int j = total + 1; j < n; j++) {
				if (testArray[total].equals(reverse(testArray[j]))) {
					count++;
				}
			}
			total++;
		}
		return Integer.toString(count);
	}

	private static String reverse(String str) {
		StringBuilder reversed = new StringBuilder(str);
		return reversed.reverse().toString();
	}
}
