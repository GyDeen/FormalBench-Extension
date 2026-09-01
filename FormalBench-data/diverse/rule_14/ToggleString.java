import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ToggleString {

	public static String toggleString(String string) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < string.length(); i++) {
			char ch = string.charAt(i);
			if (!(Character.isUpperCase(ch))) {
				sb.append(Character.toUpperCase(ch));
			} else {
				sb.append(Character.toLowerCase(ch));
			}
		}
		return sb.toString();
	}
}
