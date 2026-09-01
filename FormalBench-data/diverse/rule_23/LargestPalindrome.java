
import java.io.*;
import java.lang.*;
import java.math.*;

class LargestPalindrome {

	public static boolean isPalindrome(String s) {
		for (int i = 0; s.length() / 2 > i; i++) {
			if (s.charAt(s.length() - 1 - i) != s.charAt(i)) {
				return false;
			}
		}
		return true;
	}

	public static int largestPalindrome(int[] a, int n) {
		int max = Integer.MIN_VALUE;
		for (int i = 0; n > i; i++) {
			if (isPalindrome(String.valueOf(a[i]))) {
				max = Math.max(max, a[i]);
			}
		}
		return max == Integer.MIN_VALUE ? -1 : max;
	}
}
