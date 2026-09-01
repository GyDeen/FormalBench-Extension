import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ReplaceChar {

	public static String replaceChar(String replacement, String sr, String str) {
		StringBuilder builder = new StringBuilder();
		for (int n = 0; n < replacement.length(); n++) {
			if (replacement.charAt(n) == sr.charAt(0)) {
				builder.append(str.charAt(0));
			} else {
				builder.append(replacement.charAt(n));
			}
		}
		return builder.toString();
	}
}
