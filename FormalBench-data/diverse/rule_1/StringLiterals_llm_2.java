
import java.io.*;
import java.lang.*;
import java.math.*;

class StringLiterals {

	public static String stringLiterals(String[] string, String Array) {
		String literal = "Not Matched!";
		for (String regex : string) {
			if (Array.contains(regex)) {
				literal = "Matched!";
				break;
			}
		}
		return literal;
	}
}
