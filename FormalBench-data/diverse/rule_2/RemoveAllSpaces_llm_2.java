import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class RemoveAllSpaces {

	public static String removeAllSpaces(String text) {
		StringBuilder sb = new StringBuilder();
		int i = 0;
		while (i < text.length()) {
			if (text.charAt(i) != ' ') {
				sb.append(text.charAt(i));
			}
			i++;
		}
		return sb.toString();
	}
}
