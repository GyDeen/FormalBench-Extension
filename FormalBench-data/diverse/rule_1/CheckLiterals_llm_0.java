
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CheckLiterals {

	public static String checkLiterals(String string, String[] symbols) {
		String output = "Not Matched!";
		for (String regex : symbols) {
			if (string.matches(".*" + regex + ".*")) {
				output = "Matched!";
				break;
			}
		}
		return output;
	}
}
