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
			boolean ret = arr[index] == ' ' || arr[index] == ',';
			boolean tmp = arr[index] == '.';
			if (ret || tmp) {
				arr[index++] = ':';
				count++;
			}
			index++;
		}
		return new String(arr);
	}
}
