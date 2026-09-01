import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ReplaceMaxSpecialchar {

	public static String replaceMaxSpecialchar(String text, int n) {
		if (text == null) {
			return null;
		}
		char[] arr = text.toCharArray();
		int len = text.length();
		int count = 0;
		int index = 0;
		while (count < n && index < len) {
			if (arr[index] == ' ' || arr[index] == ',' || arr[index] == '.') {
				arr[index++] = ':';
				count += 1;
			}
			index += 1;
		}
		return new String(arr);
	}
}
