import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ToggleString {

	public static String toggleString(String string) {
		StringBuilder sb = new StringBuilder();
		int n = 0;
		while (n < string.length()) {
			char ch = string.charAt(n);
			if (Character.isUpperCase(ch)) {
				sb.append(Character.toLowerCase(ch));
			} else {
				sb.append(Character.toUpperCase(ch));
			}
			n++;
		}
		return sb.toString();
	}
}
