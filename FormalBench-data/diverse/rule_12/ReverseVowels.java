import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ReverseVowels {

	public static String reverseVowels(String str1) {
		char[] str = str1.toCharArray();
		String vowels = "aeiouAEIOU";
		int i = 0, j = str.length - 1;
		while (i < j) {
			while (i < j && !vowels.contains(String.valueOf(str[i]))) {
				i++;
			}
			while (i < j && !vowels.contains(String.valueOf(str[j]))) {
				j--;
			}
			char temp = str[i];
			str[i] = str[j];
			i++;
			str[j] = temp;
			j--;
		}
		return new String(str);
	}
}
