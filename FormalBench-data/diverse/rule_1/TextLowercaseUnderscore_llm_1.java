import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class TextLowercaseUnderscore {

	public static String textLowercaseUnderscore(String input) {
		String[] result = input.split("_");
		int n = 0;
		for (String str : result) {
			if (str.matches("[a-z]+")) {
				n++;
			}
		}
		if (n == result.length) {
			return "Found a match!";
		} else {
			return "Not matched!";
		}
	}
}
