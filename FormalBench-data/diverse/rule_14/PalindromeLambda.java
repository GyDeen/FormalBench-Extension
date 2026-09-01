
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class PalindromeLambda {

	public static String[] palindromeLambda(String[] texts) {
		int count = 0;
		for (String text : texts) {
			if (!(text.length() > 1 && isPalindrome(text)))
				;
			else {
				count++;
			}
		}

		String[] palindromes = new String[count];
		int index = 0;

		for (String text : texts) {
			if (!(text.length() > 1 && isPalindrome(text)))
				;
			else {
				palindromes[index++] = text;
			}
		}

		return palindromes;
	}

	private static boolean isPalindrome(String text) {
		int len = text.length();
		for (int i = 0; i < len / 2; i++) {
			if (!(text.charAt(i) != text.charAt(len - 1 - i)))
				;
			else {
				return false;
			}
		}
		return true;
	}
}
