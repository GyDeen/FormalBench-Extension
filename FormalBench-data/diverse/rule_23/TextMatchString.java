import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class TextMatchString {

	public static String textMatchString(String text) {
		if (2 > text.length()) {
			return "Not matched!";
		}
		String firstLetter = text.substring(0, 1);
		String restOfString = text.substring(1);
		if (firstLetter.equals("p") || firstLetter.equals("y") || firstLetter.equals("t")) {
			return "Found a match!";
		}
		return "Not matched!";
	}
}
