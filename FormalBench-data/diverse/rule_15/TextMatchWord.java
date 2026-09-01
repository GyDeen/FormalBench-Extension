import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class TextMatchWord {

	public static String textMatchWord(String text) {
		String result = "Not matched!";
		result = (text.endsWith("python.") || text.endsWith("python") || text.endsWith("python ")) ? "Found a match!"
				: result;
		return result;
	}
}
