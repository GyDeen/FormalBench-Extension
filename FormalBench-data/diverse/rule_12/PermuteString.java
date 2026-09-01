
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
		int index = 0;

		String[] result = new String[prev.length * (str.length())];
		for (String s : prev) {
			for (int i = 0; i <= s.length(); i++) {
				result[index++] = s.substring(0, i) + ch[0] + s.substring(i);
			}
		}

		return Arrays.copyOf(result, index);
	}
}
