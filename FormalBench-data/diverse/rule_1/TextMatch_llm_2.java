import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class TextMatch {

	public static String textMatch(String pattern) {
		String result = "a(\\w+)\\1*";
		if (pattern.matches(result)) {
			return "Found a match!";
		} else {
			return "Not matched!";
		}
	}
}
