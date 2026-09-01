import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class RemoveOcc {

	public static String removeOcc(String str, String c) {
		StringBuilder builder = new StringBuilder();
		int n = 0;
		int len = str.length() - 1;
		while (n < str.length() && len >= 0) {
			if (str.charAt(n) != c.charAt(0)
					&& str.charAt(len) != c.charAt(0)) {
				builder.append(str.charAt(n));
				n++;
			} else if (str.charAt(n) != c.charAt(0)
					&& str.charAt(len) == c.charAt(0)) {
				n++;
			} else if (str.charAt(n) == c.charAt(0)
					&& str.charAt(len) != c.charAt(0)) {
				len--;
			} else {
				n++;
				len--;
			}
		}
		return builder.toString();
	}
}
