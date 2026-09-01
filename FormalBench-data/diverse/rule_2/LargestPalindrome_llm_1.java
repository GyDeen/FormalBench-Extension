
import java.io.*;
import java.lang.*;
import java.math.*;

class LargestPalindrome {

	public static boolean isPalindrome(String s) {
		int p = 0;
		while (p < s.length() / 2) {
			if (s.charAt(p) != s.charAt(s.length() - 1 - p)) {
				return false;
			}
			p++;
		}
		return true;
	}

	public static int largestPalindrome(int[] a, int n) {
		int max = Integer.MIN_VALUE;
		int p = 0;
		while (p < n) {
			if (isPalindrome(String.valueOf(a[p]))) {
				max = Math.max(max, a[p]);
			}
			p++;
		}
		return max == Integer.MIN_VALUE ? -1 : max;
	}
}
