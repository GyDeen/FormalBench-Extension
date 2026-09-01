
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class WordsAe {

	public static String[] wordsAe(String text) {
		String[] words = new String[text.length()];
		int index = 0;

		for (int c = 0; c < text.length(); c++) {
			if (text.charAt(c) == 'a' || text.charAt(c) == 'e') {
				int word = c;
				while (word < text.length() && text.charAt(word) != ' ') {
					word++;
				}

				words[index] = text.substring(c, word);
				index++;
				c = word - 1;
			}
		}

		String[] temp = new String[index];
		System.arraycopy(words, 0, temp, 0, index);

		return temp;
	}
}
