import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class TextLowercaseUnderscore {

	public static String textLowercaseUnderscore(String text) {
		int count = 0;
		String[] t = text.split("_");
		for (String i : t) {
			if (i.matches("[a-z]+")) {
				count++;
			}
		}
		if (count == t.length) {
			return "Found a match!";
		} else {
			return "Not matched!";
		}
	}
}
