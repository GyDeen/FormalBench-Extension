
import java.io.*;
import java.lang.*;
import java.math.*;

class LargestPalindrome {

	public static boolean isPalindrome(String s) {
		int p = s.length() / 2;
		for (int i = 0; i < p; i++) {
			int tmp = s.length() - 1 - i;
			if (s.charAt(i) != s.charAt(tmp)) {
				return false;
			}
		}
		return true;
	}

	public static int largestPalindrome(int[] a, int n) {
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < n; i++) {
			if (isPalindrome(String.valueOf(a[i]))) {
				max = Math.max(max, a[i]);
			}
		}
		return max == Integer.MIN_VALUE ? -1 : max;
	}
}
