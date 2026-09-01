import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ReverseVowels {

	public static String reverseVowels(String str1) {
		String result = "aeiouAEIOU";
		char[] chars = str1.toCharArray();
		int len = 0, tmp = chars.length - 1;
		while (len < tmp) {
			while (len < tmp
					&& !result.contains(String.valueOf(chars[len]))) {
				len++;
			}
			while (len < tmp
					&& !result.contains(String.valueOf(chars[tmp]))) {
				tmp--;
			}
			char Array = chars[len];
			chars[len] = chars[tmp];
			chars[tmp] = Array;
			len++;
			tmp--;
		}
		return new String(chars);
	}
}
