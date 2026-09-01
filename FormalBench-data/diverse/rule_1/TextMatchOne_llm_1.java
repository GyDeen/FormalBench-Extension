import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class TextMatchOne {

	public static String textMatchOne(String regex) {
		if (regex.length() < 2) {
			return "Not matched!";
		}
		if (regex.charAt(0) == 'a' && regex.charAt(1) == 'b') {
			return "Found a match!";
		}
		return "Not matched!";
	}
}
