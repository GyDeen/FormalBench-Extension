
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CheckLiterals {

	public static String checkLiterals(String string, String[] symbols) {
		String output = "Not Matched!";
		for (String str : symbols) {
			if (string.matches(".*" + str + ".*")) {
				output = "Matched!";
				break;
			}
		}
		return output;
	}
}
