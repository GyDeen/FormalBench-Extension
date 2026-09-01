
import java.io.*;
import java.lang.*;
import java.math.*;

class StringLiterals {

	public static String stringLiterals(String[] strings, String Array) {
		String literal = "Not Matched!";
		for (String string : strings) {
			if (Array.contains(string)) {
				literal = "Matched!";
				break;
			}
		}
		return literal;
	}
}
