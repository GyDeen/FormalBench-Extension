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
		int start = 0;
		while (count < max && start < ret) {
			if (chars[start] == ' ' || chars[start] == ','
					|| chars[start] == '.') {
				chars[start++] = ':';
				count++;
			}
			start++;
		}
		return new String(chars);
	}
}
