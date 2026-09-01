import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CountSubstrings {

	public static int countSubstrings(String Builder, int b) {
		int count = 0;
		for (int c = 0; c < b; c++) {
			for (int index = c + 1; index <= b; index++) {
				String sub = Builder.substring(c, index);
				int end = 0;
				for (int k = 0; k < sub.length(); k++) {
					end += sub.charAt(k) - '0';
				}
				if (end == sub.length()) {
					count++;
				}
			}
		}
		return count;
	}
}
