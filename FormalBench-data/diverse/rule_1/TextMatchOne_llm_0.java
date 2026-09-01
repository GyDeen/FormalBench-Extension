import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class TextMatchOne {

	public static String textMatchOne(String string) {
		if (string.length() < 2) {
			return "Not matched!";
		}
		if (string.charAt(0) == 'a' && string.charAt(1) == 'b') {
			return "Found a match!";
		}
		return "Not matched!";
	}
}
