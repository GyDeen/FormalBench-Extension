
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class PermuteString {

	public static String[] permuteString(String str) {
		if (str.length() == 0) {
			return new String[] { "" };
		}

		char[] ch = str.toCharArray();
		String[] prev = permuteString(str.substring(1));
		String[] result = new String[prev.length * (str.length())];

		int index = 0;
		for (String s : prev) {
			int j = 0;
			while (j <= s.length()) {
				result[index++] = s.substring(0, j) + ch[0] + s.substring(j);
				j++;
			}
		}

		return Arrays.copyOf(result, index);
	}
}
