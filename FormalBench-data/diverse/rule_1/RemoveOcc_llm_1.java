import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class RemoveOcc {

	public static String removeOcc(String string, String c) {
		StringBuilder builder = new StringBuilder();
		int ret = 0;
		int n = string.length() - 1;
		while (ret < string.length() && n >= 0) {
			if (string.charAt(ret) != c.charAt(0)
					&& string.charAt(n) != c.charAt(0)) {
				builder.append(string.charAt(ret));
				ret++;
			} else if (string.charAt(ret) != c.charAt(0)
					&& string.charAt(n) == c.charAt(0)) {
				ret++;
			} else if (string.charAt(ret) == c.charAt(0)
					&& string.charAt(n) != c.charAt(0)) {
				n--;
			} else {
				ret++;
				n--;
			}
		}
		return builder.toString();
	}
}
