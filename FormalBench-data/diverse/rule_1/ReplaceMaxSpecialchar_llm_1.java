import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ReplaceMaxSpecialchar {

	public static String replaceMaxSpecialchar(String string, int max) {
		if (string == null) {
			return null;
		}
		char[] characters = string.toCharArray();
		int ret = string.length();
		int count = 0;
		int end = 0;
		while (count < max && end < ret) {
			if (characters[end] == ' ' || characters[end] == ','
					|| characters[end] == '.') {
				characters[end++] = ':';
				count++;
			}
			end++;
		}
		return new String(characters);
	}
}
