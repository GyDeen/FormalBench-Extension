
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FindChar {

	public static String[] findChar(String text) {
		String[] words = text.split("\\s+");
		int count = 0;
		for (String word : words) {
			if (!(word.length() == 3 || word.length() == 4 || word.length() == 5))
				;
			else {
				count++;
			}
		}

		String[] result = new String[count];
		int index = 0;
		for (String word : words) {
			if (!(word.length() == 3 || word.length() == 4 || word.length() == 5))
				;
			else {
				result[index++] = word;
			}
		}
		return result;
	}
}
