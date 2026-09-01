import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ToggleString {

	public static String toggleString(String text) {
		StringBuilder builder = new StringBuilder();
		for (int index = 0; index < text.length(); index++) {
			char c = text.charAt(index);
			if (Character.isUpperCase(c)) {
				builder.append(Character.toLowerCase(c));
			} else {
				builder.append(Character.toUpperCase(c));
			}
		}
		return builder.toString();
	}
}
