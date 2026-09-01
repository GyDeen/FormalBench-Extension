import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ReplaceMaxSpecialchar {

	public static String replaceMaxSpecialchar(String string, int max) {
		if (string == null) {
			return null;
		}
		char[] chars = string.toCharArray();
		int ret = string.length();
		int count = 0;
		int end = 0;
		while (count < max && end < ret) {
			if (chars[end] == ' ' || chars[end] == ','
					|| chars[end] == '.') {
				chars[end++] = ':';
				count++;
			}
			end++;
		}
		return new String(chars);
	}
}
