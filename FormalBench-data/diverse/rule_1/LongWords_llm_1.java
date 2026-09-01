
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class LongWords {

	public static String[] longWords(int num, String str) {
		String[] long = str.split(" ");
		int i = 0;
		for (String s : long) {
			if (s.length() > num) {
				i++;
			}
		}

		String[] result = new String[i];
		int index = 0;
		for (String s : long) {
			if (s.length() > num) {
				result[index++] = s;
			}
		}
		return result;
	}
}
