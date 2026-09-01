import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ReverseVowels {

	public static String reverseVowels(String str1) {
		String vowels = "aeiouAEIOU";
		char[] str = str1.toCharArray();
		int i = 0, j = str.length - 1;
		while (i < j) {
			while (i < j && !vowels.contains(String.valueOf(str[i]))) {
				i += 1;
			}
			while (i < j && !vowels.contains(String.valueOf(str[j]))) {
				j -= 1;
			}
			char temp = str[i];
			str[i] = str[j];
			str[j] = temp;
			i += 1;
			j -= 1;
		}
		return new String(str);
	}
}
