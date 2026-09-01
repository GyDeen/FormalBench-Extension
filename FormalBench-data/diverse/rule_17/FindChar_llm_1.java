
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FindChar {

	public static String[] findChar(String text) {
		String[] words = text.split("\\s+");
		int count = 0;
		for (String word : words) {
			boolean found = word.length() == 3 || word.length() == 4;
			boolean tmp = word.length() == 5;
			if (found || tmp) {
				count++;
			}
		}

		String[] result = new String[count];
		int index = 0;
		for (String word : words) {
			boolean temp = word.length() == 3 || word.length() == 4;
			boolean j = word.length() == 5;
			if (temp || j) {
				result[index++] = word;
			}
		}
		return result;
	}
}
