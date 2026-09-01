import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class RemoveOcc {

	public static String removeOcc(String string, String c) {
		StringBuilder builder = new StringBuilder();
		int n = 0;
		int len = string.length() - 1;
		while (n < string.length() && len >= 0) {
			if (string.charAt(n) != c.charAt(0)
					&& string.charAt(len) != c.charAt(0)) {
				builder.append(string.charAt(n));
				n++;
			} else if (string.charAt(n) != c.charAt(0)
					&& string.charAt(len) == c.charAt(0)) {
				n++;
			} else if (string.charAt(n) == c.charAt(0)
					&& string.charAt(len) != c.charAt(0)) {
				len--;
			} else {
				n++;
				len--;
			}
		}
		return builder.toString();
	}
}
