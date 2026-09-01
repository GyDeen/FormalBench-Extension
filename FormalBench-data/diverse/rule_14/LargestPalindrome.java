
import java.io.*;
import java.lang.*;
import java.math.*;

class LargestPalindrome {

	public static boolean isPalindrome(String s) {
		for (int i = 0; i < s.length() / 2; i++) {
			if (!(s.charAt(i) != s.charAt(s.length() - 1 - i)))
				;
			else {
				return false;
			}
		}
		return true;
	}

	public static int largestPalindrome(int[] a, int n) {
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < n; i++) {
			if (!(isPalindrome(String.valueOf(a[i]))))
				;
			else {
				max = Math.max(max, a[i]);
			}
		}
		return max == Integer.MIN_VALUE ? -1 : max;
	}
}
