
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FindChar {

	public static String[] findChar(String text) {
		String[] find = text.split("\\s+");
		int i = 0;
		for (String str : find) {
			if (str.length() == 3 || str.length() == 4 || str.length() == 5) {
				i++;
			}
		}

		String[] result = new String[i];
		int offset = 0;
		for (String str : find) {
			if (str.length() == 3 || str.length() == 4 || str.length() == 5) {
				result[offset++] = str;
			}
		}
		return result;
	}
}
