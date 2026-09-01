import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CountAlphaDigSpl {

	public static List<Integer> countAlphaDigSpl(String string) {
		List<Integer> alphabets = new ArrayList<>();
		List<Integer> digits = new ArrayList<>();
		List<Integer> specials = new ArrayList<>();
		for (int i = 0; i < string.length(); i++) {
			char ch = string.charAt(i);
			if (!(Character.isAlphabetic(ch))) {
				if (Character.isDigit(ch)) {
					digits.add(1);
				} else if (Character.isWhitespace(ch)) {
					continue;
				} else {
					specials.add(1);
				}
			} else {
				alphabets.add(1);
			}
		}
		return Arrays.asList(alphabets.size(), digits.size(), specials.size());
	}
}
