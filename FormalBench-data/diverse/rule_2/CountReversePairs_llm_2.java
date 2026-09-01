
import java.io.*;
import java.lang.*;
import java.math.*;

class CountReversePairs {

	public static String countReversePairs(String[] testArray) {
		int count = 0;
		int n = testArray.length;

		int max = 0;
		while (max < n) {
			for (int j = max + 1; j < n; j++) {
				if (testArray[max].equals(reverse(testArray[j]))) {
					count++;
				}
			}
			max++;
		}
		return Integer.toString(count);
	}

	private static String reverse(String str) {
		StringBuilder reversed = new StringBuilder(str);
		return reversed.reverse().toString();
	}
}
