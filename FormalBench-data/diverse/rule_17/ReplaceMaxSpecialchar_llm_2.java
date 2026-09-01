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
		boolean result = count < n;
		boolean temp = index < len;
		while (result && temp) {
			boolean tmp = arr[index] == ' ' || arr[index] == ',';
			boolean is = arr[index] == '.';
			if (tmp || is) {
				arr[index++] = ':';
				count++;
			}
			index++;
		}
		return new String(arr);
	}
}
