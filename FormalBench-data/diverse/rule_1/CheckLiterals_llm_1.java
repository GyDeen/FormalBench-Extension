
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CheckLiterals {

	public static String checkLiterals(String input, String[] symbols) {
		String output = "Not Matched!";
		for (String string : symbols) {
			if (input.matches(".*" + string + ".*")) {
				output = "Matched!";
				break;
			}
		}
		return output;
	}
}
