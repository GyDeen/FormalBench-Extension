import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class TextMatchString {

	public static String textMatchString(String string) {
		if (string.length() < 2) {
			return "Not matched!";
		}
		String result = string.substring(0, 1);
		String second = string.substring(1);
		if (result.equals("p") || result.equals("y")
				|| result.equals("t")) {
			return "Found a match!";
		}
		return "Not matched!";
	}
}
