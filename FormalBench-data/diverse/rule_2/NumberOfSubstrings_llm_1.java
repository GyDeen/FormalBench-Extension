import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class NumberOfSubstrings {

	public static int numberOfSubstrings(String str) {
		int count = 0;
		int start = 0;
		while (start < str.length()) {
			for (int j = start + 1; j <= str.length(); j++) {
				if (str.substring(start, j).length() > 0) {
					count++;
				}
			}
			start++;
		}
		return count;
	}
}
