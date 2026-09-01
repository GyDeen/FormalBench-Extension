import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class TextMatchString {

	public static String textMatchString(String str) {
		if (str.length() < 2) {
			return "Not matched!";
		}
		String result = str.substring(0, 1);
		String second = str.substring(1);
		if (result.equals("p") || result.equals("y")
				|| result.equals("t")) {
			return "Found a match!";
		}
		return "Not matched!";
	}
}
