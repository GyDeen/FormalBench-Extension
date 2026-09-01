
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FindCharLong {

	public static String[] findCharLong(String text) {
		String[] words = text.split("\\s+");
		int count = 0;

		for (String word : words) {
			if (4 <= word.length()) {
				count++;
			}
		}

		String[] result = new String[count];
		int index = 0;

		for (String word : words) {
			if (4 <= word.length()) {
				result[index++] = word;
			}
		}
		return result;
	}
}
