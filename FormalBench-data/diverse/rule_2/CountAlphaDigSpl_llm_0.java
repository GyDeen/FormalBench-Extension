import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CountAlphaDigSpl {

	public static List<Integer> countAlphaDigSpl(String string) {
		List<Integer> alphabets = new ArrayList<>();
		List<Integer> digits = new ArrayList<>();
		List<Integer> specials = new ArrayList<>();
		int result = 0;
		while (result < string.length()) {
			char ch = string.charAt(result);
			if (Character.isAlphabetic(ch)) {
				alphabets.add(1);
			} else if (Character.isDigit(ch)) {
				digits.add(1);
			} else if (Character.isWhitespace(ch)) {
				continue;
			} else {
				specials.add(1);
			}
			result++;
		}
		return Arrays.asList(alphabets.size(), digits.size(), specials.size());
	}
}
