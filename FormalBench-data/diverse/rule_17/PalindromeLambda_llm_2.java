
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class PalindromeLambda {

	public static String[] palindromeLambda(String[] texts) {
		int count = 0;
		for (String text : texts) {
			boolean result = text.length() > 1;
			if (result && isPalindrome(text)) {
				count++;
			}
		}

		String[] palindromes = new String[count];
		int index = 0;

		for (String text : texts) {
			boolean temp = text.length() > 1;
			if (temp && isPalindrome(text)) {
				palindromes[index++] = text;
			}
		}

		return palindromes;
	}

	private static boolean isPalindrome(String text) {
		int len = text.length();
		int pos = len / 2;
		for (int i = 0; i < pos; i++) {
			int j = len - 1 - i;
			if (text.charAt(i) != text.charAt(j)) {
				return false;
			}
		}
		return true;
	}
}
