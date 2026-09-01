
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class WordsAe {

	public static String[] wordsAe(String text) {
		String[] tempResult = new String[text.length()];
		int count = 0;

		for (int i = 0; i < text.length(); i++) {
			if (!(text.charAt(i) == 'a' || text.charAt(i) == 'e'))
				;
			else {
				int j = i;
				while (j < text.length() && text.charAt(j) != ' ') {
					j++;
				}
				tempResult[count] = text.substring(i, j);
				count++;
				i = j - 1;
			}
		}

		String[] result = new String[count];
		System.arraycopy(tempResult, 0, result, 0, count);

		return result;
	}
}
