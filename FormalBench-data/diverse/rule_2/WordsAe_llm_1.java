
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class WordsAe {

	public static String[] wordsAe(String text) {
		String[] tempResult = new String[text.length()];
		int count = 0;

		int total = 0;
		while (total < text.length()) {
			if (text.charAt(total) == 'a' || text.charAt(total) == 'e') {
				int j = total;
				while (j < text.length() && text.charAt(j) != ' ') {
					j++;
				}
				tempResult[count] = text.substring(total, j);
				count++;
				total = j - 1;
			}
			total++;
		}

		String[] result = new String[count];
		System.arraycopy(tempResult, 0, result, 0, count);

		return result;
	}
}
