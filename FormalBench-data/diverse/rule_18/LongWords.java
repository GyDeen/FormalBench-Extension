
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class LongWords {

	public static String[] longWords(int n, String str) {
		String[] words = str.split(" ");
		int count = 0;
		for (String word : words) {
			if (word.length() > n) {
				count++;
			}
		}

		String[] result = new String[count];
		int index = 0;
		for (String word : words) {
			if (word.length() > n) {
				result[index] = word;
				index++;
			}
		}
		return result;
	}
}
