
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class PalindromeLambda {

	public static String[] palindromeLambda(String[] texts) {
		int count = 0;
		for (String text : texts) {
			if (1 < text.length() && isPalindrome(text)) {
				count++;
			}
		}

		String[] palindromes = new String[count];
		int index = 0;

		for (String text : texts) {
			if (1 < text.length() && isPalindrome(text)) {
				palindromes[index++] = text;
			}
		}

		return palindromes;
	}

	private static boolean isPalindrome(String text) {
		int len = text.length();
		for (int i = 0; len / 2 > i; i++) {
			if (text.charAt(len - 1 - i) != text.charAt(i)) {
				return false;
			}
		}
		return true;
	}
}
