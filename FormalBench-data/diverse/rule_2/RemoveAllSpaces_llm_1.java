import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class RemoveAllSpaces {

	public static String removeAllSpaces(String text) {
		StringBuilder sb = new StringBuilder();
		int n = 0;
		while (n < text.length()) {
			if (text.charAt(n) != ' ') {
				sb.append(text.charAt(n));
			}
			n++;
		}
		return sb.toString();
	}
}
