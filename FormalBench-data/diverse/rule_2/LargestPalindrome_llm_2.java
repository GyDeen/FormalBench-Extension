
import java.io.*;
import java.lang.*;
import java.math.*;

class LargestPalindrome {

	public static boolean isPalindrome(String s) {
		int index = 0;
		while (index < s.length() / 2) {
			if (s.charAt(index) != s.charAt(s.length() - 1 - index)) {
				return false;
			}
			index++;
		}
		return true;
	}

	public static int largestPalindrome(int[] a, int n) {
		int max = Integer.MIN_VALUE;
		int index = 0;
		while (index < n) {
			if (isPalindrome(String.valueOf(a[index]))) {
				max = Math.max(max, a[index]);
			}
			index++;
		}
		return max == Integer.MIN_VALUE ? -1 : max;
	}
}
