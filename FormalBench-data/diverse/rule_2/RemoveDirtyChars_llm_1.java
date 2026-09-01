import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class RemoveDirtyChars {

	public static String removeDirtyChars(String string, String secondString) {
		StringBuilder sb = new StringBuilder();
		int len = 0;
		while (len < string.length()) {
			if (!secondString.contains(string.charAt(len) + "")) {
				sb.append(string.charAt(len));
			}
			len++;
		}
		return sb.toString();
	}
}
