
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
				int word = c;
				while (word < text.length() && text.charAt(word) != ' ') {
					word++;
				}

				words[offset] = text.substring(c, word);
				offset++;
				c = word - 1;
			}
		}

		String[] temp = new String[offset];
		System.arraycopy(words, 0, temp, 0, offset);

		return temp;
	}
}
