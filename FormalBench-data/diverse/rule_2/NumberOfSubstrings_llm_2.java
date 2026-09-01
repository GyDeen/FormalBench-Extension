import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class NumberOfSubstrings {

	public static int numberOfSubstrings(String str) {
		int count = 0;
		int total = 0;
		while (total < str.length()) {
			for (int j = total + 1; j <= str.length(); j++) {
				if (str.substring(total, j).length() > 0) {
					count++;
				}
			}
			total++;
		}
		return count;
	}
}
