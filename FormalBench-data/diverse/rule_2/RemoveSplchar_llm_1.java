import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class RemoveSplchar {

	public static String removeSplchar(String text) {
		StringBuilder sb = new StringBuilder();
		int i = 0;
		while (i < text.length()) {
			if (Character.isLetterOrDigit(text.charAt(i))) {
				sb.append(text.charAt(i));
			}
			i++;
		}
		return sb.toString();
	}
}
