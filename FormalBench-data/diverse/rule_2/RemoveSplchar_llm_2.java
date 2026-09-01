import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class RemoveSplchar {

	public static String removeSplchar(String text) {
		StringBuilder sb = new StringBuilder();
		int len = 0;
		while (len < text.length()) {
			if (Character.isLetterOrDigit(text.charAt(len))) {
				sb.append(text.charAt(len));
			}
			len++;
		}
		return sb.toString();
	}
}
