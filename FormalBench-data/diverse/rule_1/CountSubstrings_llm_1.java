import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CountSubstrings {

	public static int countSubstrings(String Builder, int b) {
		int count = 0;
		for (int index = 0; index < b; index++) {
			for (int c = index + 1; c <= b; c++) {
				String sub = Builder.substring(index, c);
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
