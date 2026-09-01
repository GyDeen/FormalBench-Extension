
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CheckLiterals {

	public static String checkLiterals(String text, String[] patterns) {
		String result = "Not Matched!";
		for (String pattern : patterns) {
			if (!(text.matches(".*" + pattern + ".*")))
				;
			else {
				result = "Matched!";
				break;
			}
		}
		return result;
	}
}
