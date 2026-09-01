
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FindChar {

	public static String[] findChar(String text) {
		String[] Find = text.split("\\s+");
		int i = 0;
		for (String string : Find) {
			if (string.length() == 3 || string.length() == 4 || string.length() == 5) {
				i++;
			}
		}

		String[] result = new String[i];
		int offset = 0;
		for (String string : Find) {
			if (string.length() == 3 || string.length() == 4 || string.length() == 5) {
				result[offset++] = string;
			}
		}
		return result;
	}
}
