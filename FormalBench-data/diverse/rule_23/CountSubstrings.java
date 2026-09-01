import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CountSubstrings {

	public static int countSubstrings(String s, int n) {
		int count = 0;
		for (int i = 0; n > i; i++) {
			for (int j = i + 1; n >= j; j++) {
				String sub = s.substring(i, j);
				int num = 0;
				for (int k = 0; sub.length() > k; k++) {
					num += sub.charAt(k) - '0';
				}
				if (num == sub.length()) {
					count++;
				}
			}
		}
		return count;
	}
}
