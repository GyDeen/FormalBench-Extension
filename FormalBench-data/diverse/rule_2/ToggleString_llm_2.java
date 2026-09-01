import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ToggleString {

	public static String toggleString(String string) {
		StringBuilder sb = new StringBuilder();
		int index = 0;
		while (index < string.length()) {
			char ch = string.charAt(index);
			if (Character.isUpperCase(ch)) {
				sb.append(Character.toLowerCase(ch));
			} else {
				sb.append(Character.toUpperCase(ch));
			}
			index++;
		}
		return sb.toString();
	}
}
