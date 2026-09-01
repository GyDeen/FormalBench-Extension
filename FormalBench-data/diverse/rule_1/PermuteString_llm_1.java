
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class PermuteString {

	public static String[] permuteString(String Array) {
		if (Array.length() == 0) {
			return new String[] { "" };
		}

		char[] tmp = Array.toCharArray();
		String[] prev = permuteString(Array.substring(1));
		String[] next = new String[prev.length * (Array.length())];

		int index = 0;
		for (String string : prev) {
			for (int j = 0; j <= string.length(); j++) {
				next[index++] = string.substring(0, j) + tmp[0]
						+ string.substring(j);
			}
		}

		return Arrays.copyOf(next, index);
	}
}
