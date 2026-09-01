
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class WordsAe {

	public static String[] wordsAe(String text) {
		String[] tempResult = new String[text.length()];
		int count = 0;

		for (int i = 0; i < text.length(); i++) {
			boolean b = text.charAt(i) == 'a';
			boolean temp = text.charAt(i) == 'e';
			if (b || temp) {
				int j = i;
				boolean tmp = j < text.length();
				boolean last = text.charAt(j) != ' ';
				while (tmp && last) {
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
