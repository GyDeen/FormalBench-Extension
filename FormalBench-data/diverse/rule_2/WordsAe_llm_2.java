
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class WordsAe {

	public static String[] wordsAe(String text) {
		String[] tempResult = new String[text.length()];
		int count = 0;

		int end = 0;
		while (end < text.length()) {
			if (text.charAt(end) == 'a' || text.charAt(end) == 'e') {
				int j = end;
				while (j < text.length() && text.charAt(j) != ' ') {
					j++;
				}
				tempResult[count] = text.substring(end, j);
				count++;
				end = j - 1;
			}
			end++;
		}

		String[] result = new String[count];
		System.arraycopy(tempResult, 0, result, 0, count);

		return result;
	}
}
