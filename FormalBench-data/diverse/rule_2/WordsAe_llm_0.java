
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class WordsAe {

	public static String[] wordsAe(String text) {
		String[] tempResult = new String[text.length()];
		int count = 0;

		int offset = 0;
		while (offset < text.length()) {
			if (text.charAt(offset) == 'a' || text.charAt(offset) == 'e') {
				int j = offset;
				while (j < text.length() && text.charAt(j) != ' ') {
					j++;
				}
				tempResult[count] = text.substring(offset, j);
				count++;
				offset = j - 1;
			}
			offset++;
		}

		String[] result = new String[count];
		System.arraycopy(tempResult, 0, result, 0, count);

		return result;
	}
}
