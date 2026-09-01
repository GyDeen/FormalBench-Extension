import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class TextMatchString {

	public static String textMatchString(String text) {
		if (text.length() < 2) {
			return "Not matched!";
		}
		String firstLetter = text.substring(0, 1);
		String restOfString = text.substring(1);
		if ("p".equals(firstLetter) || "y".equals(firstLetter) || "t".equals(firstLetter)) {
			return "Found a match!";
		}
		return "Not matched!";
	}
}
