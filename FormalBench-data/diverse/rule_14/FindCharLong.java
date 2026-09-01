
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FindCharLong {

	public static String[] findCharLong(String text) {
		String[] words = text.split("\\s+");
		int count = 0;

		for (String word : words) {
			if (!(word.length() >= 4))
				;
			else {
				count++;
			}
		}

		String[] result = new String[count];
		int index = 0;

		for (String word : words) {
			if (!(word.length() >= 4))
				;
			else {
				result[index++] = word;
			}
		}
		return result;
	}
}
