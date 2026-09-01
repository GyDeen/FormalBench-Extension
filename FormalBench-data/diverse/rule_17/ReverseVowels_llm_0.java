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
			boolean b = i < j;
			while (b && !vowels.contains(String.valueOf(str[i]))) {
				i++;
			}
			boolean tmp = i < j;
			while (tmp && !vowels.contains(String.valueOf(str[j]))) {
				j--;
			}
			char temp = str[i];
			str[i] = str[j];
			str[j] = temp;
			i++;
			j--;
		}
		return new String(str);
	}
}
