import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class TextMatchWordzMiddle {

	public static String textMatchWordzMiddle(String text) {
		String result = "Not matched!";
		if (text.contains("z")) {
			int index = text.indexOf("z");
			result = (index != 0 && index != text.length() - 1) ? "Found a match!" : result;
		}
		return result;
	}
}
