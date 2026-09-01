import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class RemoveSplchar {

	public static String removeSplchar(String text) {
		StringBuilder sb = new StringBuilder();
		int n = 0;
		while (n < text.length()) {
			if (Character.isLetterOrDigit(text.charAt(n))) {
				sb.append(text.charAt(n));
			}
			n++;
		}
		return sb.toString();
	}
}
