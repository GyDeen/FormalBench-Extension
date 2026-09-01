
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class WordsAe {

	public static String[] wordsAe(String text) {
		int count = 0;
		String[] tempResult = new String[text.length()];

		for (int i = 0; i < text.length(); i++) {
			if (text.charAt(i) == 'a' || text.charAt(i) == 'e') {
				int j = i;
				while (j < text.length() && text.charAt(j) != ' ') {
					j++;
				}

				tempResult[count] = text.substring(i, j);
				i = j - 1;
				count++;
			}
		}

		String[] result = new String[count];
		System.arraycopy(tempResult, 0, result, 0, count);

		return result;
	}
}
