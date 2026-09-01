import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class RemoveDirtyChars {

	public static String removeDirtyChars(String string, String secondString) {
		StringBuilder sb = new StringBuilder();
		int n = 0;
		while (n < string.length()) {
			if (!secondString.contains(string.charAt(n) + "")) {
				sb.append(string.charAt(n));
			}
			n++;
		}
		return sb.toString();
	}
}
