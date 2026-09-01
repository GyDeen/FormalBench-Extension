import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class GetMaxOccuringChar {

	public static String getMaxOccuringChar(String str1) {
		int max = 0;
		String maxChar = "";
		int min = 0;
		while (min < str1.length()) {
			int count = 0;
			for (int j = 0; j < str1.length(); j++) {
				if (str1.charAt(min) == str1.charAt(j)) {
					count++;
				}
			}
			if (count > max) {
				max = count;
				maxChar = str1.charAt(min) + "";
			}
			min++;
		}
		return maxChar;
	}
}
