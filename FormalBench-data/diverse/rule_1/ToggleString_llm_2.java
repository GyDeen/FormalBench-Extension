import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ToggleString {

	public static String toggleString(String text) {
		StringBuilder s = new StringBuilder();
		for (int index = 0; index < text.length(); index++) {
			char c = text.charAt(index);
			if (Character.isUpperCase(c)) {
				s.append(Character.toLowerCase(c));
			} else {
				s.append(Character.toUpperCase(c));
			}
		}
		return s.toString();
	}
}
