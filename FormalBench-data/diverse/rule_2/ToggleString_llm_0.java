import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ToggleString {

	public static String toggleString(String string) {
		StringBuilder sb = new StringBuilder();
		int i = 0;
		while (i < string.length()) {
			char ch = string.charAt(i);
			if (Character.isUpperCase(ch)) {
				sb.append(Character.toLowerCase(ch));
			} else {
				sb.append(Character.toUpperCase(ch));
			}
			i++;
		}
		return sb.toString();
	}
}
