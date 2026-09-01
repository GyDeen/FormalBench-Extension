import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CountSubstrings {

	public static int countSubstrings(String s, int n) {
		int count = 0;
		int end = 0;
		while (end < n) {
			for (int j = end + 1; j <= n; j++) {
				String sub = s.substring(end, j);
				int num = 0;
				for (int k = 0; k < sub.length(); k++) {
					num += sub.charAt(k) - '0';
				}
				if (num == sub.length()) {
					count++;
				}
			}
			end++;
		}
		return count;
	}
}
