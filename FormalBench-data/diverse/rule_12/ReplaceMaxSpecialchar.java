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
		int count = 0;
		int len = text.length();
		int index = 0;
		while (count < n && index < len) {
			if (arr[index] == ' ' || arr[index] == ',' || arr[index] == '.') {
				count++;
				arr[index++] = ':';
			}
			index++;
		}
		return new String(arr);
	}
}
