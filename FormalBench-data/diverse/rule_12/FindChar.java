
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FindChar {

	public static String[] findChar(String text) {
		int count = 0;
		String[] words = text.split("\\s+");
		for (String word : words) {
			if (word.length() == 3 || word.length() == 4 || word.length() == 5) {
				count++;
			}
		}

		int index = 0;
		String[] result = new String[count];
		for (String word : words) {
			if (word.length() == 3 || word.length() == 4 || word.length() == 5) {
				result[index++] = word;
			}
		}
		return result;
	}
}
