import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class TextLowercaseUnderscore {

	public static String textLowercaseUnderscore(String string) {
		String[] strings = string.split("_");
		int n = 0;
		for (String str : strings) {
			if (str.matches("[a-z]+")) {
				n++;
			}
		}
		if (n == strings.length) {
			return "Found a match!";
		} else {
			return "Not matched!";
		}
	}
}
