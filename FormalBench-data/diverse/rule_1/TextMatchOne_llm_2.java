import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class TextMatchOne {

	public static String textMatchOne(String str) {
		if (str.length() < 2) {
			return "Not matched!";
		}
		if (str.charAt(0) == 'a' && str.charAt(1) == 'b') {
			return "Found a match!";
		}
		return "Not matched!";
	}
}
