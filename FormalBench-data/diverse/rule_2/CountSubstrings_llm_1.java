import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CountSubstrings {

	public static int countSubstrings(String s, int n) {
		int count = 0;
		int result = 0;
		while (result < n) {
			for (int j = result + 1; j <= n; j++) {
				String sub = s.substring(result, j);
				int num = 0;
				for (int k = 0; k < sub.length(); k++) {
					num += sub.charAt(k) - '0';
				}
				if (num == sub.length()) {
					count++;
				}
			}
			result++;
		}
		return count;
	}
}
