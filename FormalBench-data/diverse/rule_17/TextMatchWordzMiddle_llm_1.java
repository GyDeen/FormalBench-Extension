import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class TextMatchWordzMiddle {

	public static String textMatchWordzMiddle(String text) {
		String result = "Not matched!";
		if (text.contains("z")) {
			int index = text.indexOf("z");
			boolean found = index != 0;
			boolean temp = index != text.length() - 1;
			if (found && temp) {
				result = "Found a match!";
			}
		}
		return result;
	}
}
