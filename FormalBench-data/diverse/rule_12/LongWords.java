
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class LongWords {

	public static String[] longWords(int n, String str) {
		int count = 0;
		String[] words = str.split(" ");
		for (String word : words) {
			if (word.length() > n) {
				count++;
			}
		}

		int index = 0;
		String[] result = new String[count];
		for (String word : words) {
			if (word.length() > n) {
				result[index++] = word;
			}
		}
		return result;
	}
}
