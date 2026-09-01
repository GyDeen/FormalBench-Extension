import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class TextUppercaseLowercase {

	public static String textUppercaseLowercase(String text) {
		String result = "Not matched!";
		int offset = 0;
		while (offset < text.length()) {
			if (Character.isUpperCase(text.charAt(offset))) {
				if (offset + 1 < text.length()
						&& Character.isLowerCase(text.charAt(offset + 1))) {
					result = "Found a match!";
					break;
				}
			}
			offset++;
		}
		return result;
	}
}
