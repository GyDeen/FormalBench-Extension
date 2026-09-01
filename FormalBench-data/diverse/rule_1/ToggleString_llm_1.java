import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ToggleString {

	public static String toggleString(String text) {
		StringBuilder builder = new StringBuilder();
		for (int index = 0; index < text.length(); index++) {
			char character = text.charAt(index);
			if (Character.isUpperCase(character)) {
				builder.append(Character.toLowerCase(character));
			} else {
				builder.append(Character.toUpperCase(character));
			}
		}
		return builder.toString();
	}
}
