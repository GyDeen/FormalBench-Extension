import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class TextMatchWord {

	public static String textMatchWord(String input) {
		String match = "Not matched!";
		if (input.endsWith("python.") || input.endsWith("python")
				|| input.endsWith("python ")) {
			match = "Found a match!";
		}
		return match;
	}
}
