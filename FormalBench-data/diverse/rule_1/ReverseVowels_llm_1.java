import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ReverseVowels {

	public static String reverseVowels(String str1) {
		String result = "aeiouAEIOU";
		char[] chars = str1.toCharArray();
		int len = 0, end = chars.length - 1;
		while (len < end) {
			while (len < end
					&& !result.contains(String.valueOf(chars[len]))) {
				len++;
			}
			while (len < end
					&& !result.contains(String.valueOf(chars[end]))) {
				end--;
			}
			char c = chars[len];
			chars[len] = chars[end];
			chars[end] = c;
			len++;
			end--;
		}
		return new String(chars);
	}
}
