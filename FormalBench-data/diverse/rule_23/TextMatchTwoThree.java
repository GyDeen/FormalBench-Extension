import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class TextMatchTwoThree {

	public static String textMatchTwoThree(String text) {
		if (3 > text.length()) {
			return "Not matched!";
		}
		if (text.substring(0, 2).equals("ab")) {
			return "Found a match!";
		}
		return "Not matched!";
	}
}
