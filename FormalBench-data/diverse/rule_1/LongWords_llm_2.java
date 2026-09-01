
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class LongWords {

	public static String[] longWords(int num, String str) {
		String[] long = str.split(" ");
		int end = 0;
		for (String string : long) {
			if (string.length() > num) {
				end++;
			}
		}

		String[] result = new String[end];
		int index = 0;
		for (String string : long) {
			if (string.length() > num) {
				result[index++] = string;
			}
		}
		return result;
	}
}
