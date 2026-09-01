import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class TextMatchTwoThree {

	public static String textMatchTwoThree(String regex) {
		if (regex.length() < 3) {
			return "Not matched!";
		}
		if (regex.substring(0, 2).equals("ab")) {
			return "Found a match!";
		}
		return "Not matched!";
	}
}
