import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class TextLowercaseUnderscore {

	public static String textLowercaseUnderscore(String text) {
		String[] t = text.split("_");
		int count = 0;
		for (String i : t) {
			if (i.matches("[a-z]+")) {
				count++;
			}
		}
		if (t.length == count) {
			return "Found a match!";
		} else {
			return "Not matched!";
		}
	}
}
