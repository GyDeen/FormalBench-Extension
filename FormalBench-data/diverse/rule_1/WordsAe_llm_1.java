
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class WordsAe {

	public static String[] wordsAe(String text) {
		String[] words = new String[text.length()];
		int offset = 0;

		for (int c = 0; c < text.length(); c++) {
			if (text.charAt(c) == 'a' || text.charAt(c) == 'e') {
				int temp = c;
				while (temp < text.length() && text.charAt(temp) != ' ') {
					temp++;
				}

				words[offset] = text.substring(c, temp);
				offset++;
				c = temp - 1;
			}
		}

		String[] word = new String[offset];
		System.arraycopy(words, 0, word, 0, offset);

		return word;
	}
}
