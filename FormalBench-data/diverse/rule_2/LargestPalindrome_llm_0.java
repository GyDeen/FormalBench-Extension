
import java.io.*;
import java.lang.*;
import java.math.*;

class LargestPalindrome {

	public static boolean isPalindrome(String s) {
		int i = 0;
		while (i < s.length() / 2) {
			if (s.charAt(i) != s.charAt(s.length() - 1 - i)) {
				return false;
			}
			i++;
		}
		return true;
	}

	public static int largestPalindrome(int[] a, int n) {
		int max = Integer.MIN_VALUE;
		int i = 0;
		while (i < n) {
			if (isPalindrome(String.valueOf(a[i]))) {
				max = Math.max(max, a[i]);
			}
			i++;
		}
		return max == Integer.MIN_VALUE ? -1 : max;
	}
}
