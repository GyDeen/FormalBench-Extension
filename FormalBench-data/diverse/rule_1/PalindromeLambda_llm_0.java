
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class PalindromeLambda {

	public static String[] palindromeLambda(String[] texts) {
		int n = 0;
		for (String text : texts) {
			if (text.length() > 1 && isPalindrome(text)) {
				n++;
			}
		}

		String[] result = new String[n];
		int start = 0;

		for (String string : texts) {
			if (string.length() > 1 && isPalindrome(string)) {
				result[start++] = string;
			}
		}

		return result;
	}

	private static boolean isPalindrome(String text) {
		int alpha = text.length();
		for (int i = 0; i < alpha / 2; i++) {
			if (text.charAt(i) != text.charAt(alpha - 1 - i)) {
				return false;
			}
		}
		return true;
	}
}
