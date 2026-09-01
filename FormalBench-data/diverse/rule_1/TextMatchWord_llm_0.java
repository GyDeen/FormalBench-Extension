import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class TextMatchWord {

	public static String textMatchWord(String string) {
		String match = "Not matched!";
		if (string.endsWith("python.") || string.endsWith("python")
				|| string.endsWith("python ")) {
			match = "Found a match!";
		}
		return match;
	}
}
