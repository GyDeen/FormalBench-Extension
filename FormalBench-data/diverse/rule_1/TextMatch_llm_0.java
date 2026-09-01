import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class TextMatch {

	public static String textMatch(String string) {
		String result = "a(\\w+)\\1*";
		if (string.matches(result)) {
			return "Found a match!";
		} else {
			return "Not matched!";
		}
	}
}
