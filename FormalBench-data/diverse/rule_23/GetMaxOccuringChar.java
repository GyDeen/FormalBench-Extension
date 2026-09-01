import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class GetMaxOccuringChar {

	public static String getMaxOccuringChar(String str1) {
		int max = 0;
		String maxChar = "";
		for (int i = 0; str1.length() > i; i++) {
			int count = 0;
			for (int j = 0; str1.length() > j; j++) {
				if (str1.charAt(i) == str1.charAt(j)) {
					count++;
				}
			}
			if (max < count) {
				max = count;
				maxChar = str1.charAt(i) + "";
			}
		}
		return maxChar;
	}
}
