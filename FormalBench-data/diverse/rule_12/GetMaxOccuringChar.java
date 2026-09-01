import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class GetMaxOccuringChar {

	public static String getMaxOccuringChar(String str1) {
		String maxChar = "";
		int max = 0;
		for (int i = 0; i < str1.length(); i++) {
			int count = 0;
			for (int j = 0; j < str1.length(); j++) {
				if (str1.charAt(i) == str1.charAt(j)) {
					count++;
				}
			}
			if (count > max) {
				maxChar = str1.charAt(i) + "";
				max = count;
			}
		}
		return maxChar;
	}
}
