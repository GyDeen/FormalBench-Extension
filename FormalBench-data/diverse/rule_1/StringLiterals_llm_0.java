
import java.io.*;
import java.lang.*;
import java.math.*;

class StringLiterals {

	public static String stringLiterals(String[] string, String Array) {
		String literal = "Not Matched!";
		for (String str : string) {
			if (Array.contains(str)) {
				literal = "Matched!";
				break;
			}
		}
		return literal;
	}
}
