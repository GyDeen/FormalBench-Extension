
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FindCharLong {

	public static String[] findCharLong(String text) {
		int count = 0;
		String[] words = text.split("\\s+");

		for (String word : words) {
			if (word.length() >= 4) {
				count++;
			}
		}

		int index = 0;
		String[] result = new String[count];

		for (String word : words) {
			if (word.length() >= 4) {
				result[index++] = word;
			}
		}
		return result;
	}
}
