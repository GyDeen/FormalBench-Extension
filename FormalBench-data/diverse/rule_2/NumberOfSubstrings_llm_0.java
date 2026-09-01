import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class NumberOfSubstrings {

	public static int numberOfSubstrings(String str) {
		int count = 0;
		int end = 0;
		while (end < str.length()) {
			for (int j = end + 1; j <= str.length(); j++) {
				if (str.substring(end, j).length() > 0) {
					count++;
				}
			}
			end++;
		}
		return count;
	}
}
