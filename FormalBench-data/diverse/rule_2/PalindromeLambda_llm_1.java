
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class PalindromeLambda {

	public static String[] palindromeLambda(String[] texts) {
		int count = 0;
		for (String text : texts) {
			if (text.length() > 1 && isPalindrome(text)) {
				count++;
			}
		}

		String[] palindromes = new String[count];
		int index = 0;

		for (String text : texts) {
			if (text.length() > 1 && isPalindrome(text)) {
				palindromes[index++] = text;
			}
		}

		return palindromes;
	}

	private static boolean isPalindrome(String text) {
		int len = text.length();
		int offset = 0;
		while (offset < len / 2) {
			if (text.charAt(offset) != text.charAt(len - 1 - offset)) {
				return false;
			}
			offset++;
		}
		return true;
	}
}
