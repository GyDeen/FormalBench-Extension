import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class GetMaxOccuringChar {

	public static String getMaxOccuringChar(String str1) {
		int max = 0;
		String maxChar = "";
		for (int i = 0; i < str1.length(); i++) {
			int count = 0;
			for (int j = 0; j < str1.length(); j++) {
				if (str1.charAt(i) == str1.charAt(j)) {
					count += 1;
				}
			}
			if (count > max) {
				max = count;
				maxChar = str1.charAt(i) + "";
			}
		}
		return maxChar;
	}
}
