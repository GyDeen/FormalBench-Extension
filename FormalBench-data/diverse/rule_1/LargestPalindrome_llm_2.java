
import java.io.*;
import java.lang.*;
import java.math.*;

class LargestPalindrome {

	public static boolean isPalindrome(String string) {
		for (int c = 0; c < string.length() / 2; c++) {
			if (string.charAt(c) != string
					.charAt(string.length() - 1 - c)) {
				return false;
			}
		}
		return true;
	}

	public static int largestPalindrome(int[] a, int array) {
		int l = Integer.MIN_VALUE;
		for (int c = 0; c < array; c++) {
			if (isPalindrome(String.valueOf(a[c]))) {
				l = Math.max(l, a[c]);
			}
		}
		return l == Integer.MIN_VALUE ? -1 : l;
	}
}
