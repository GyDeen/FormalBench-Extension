import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CountSubstrings {

	public static int countSubstrings(String s, int n) {
		int count = 0;
		int start = 0;
		while (start < n) {
			for (int j = start + 1; j <= n; j++) {
				String sub = s.substring(start, j);
				int num = 0;
				for (int k = 0; k < sub.length(); k++) {
					num += sub.charAt(k) - '0';
				}
				if (num == sub.length()) {
					count++;
				}
			}
			start++;
		}
		return count;
	}
}
