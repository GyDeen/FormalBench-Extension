import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CountAlphaDigSpl {

	public static List<Integer> countAlphaDigSpl(String string) {
		List<Integer> result = new ArrayList<>();
		List<Integer> number = new ArrayList<>();
		List<Integer> sign = new ArrayList<>();
		for (int index = 0; index < string.length(); index++) {
			char c = string.charAt(index);
			if (Character.isAlphabetic(c)) {
				result.add(1);
			} else if (Character.isDigit(c)) {
				number.add(1);
			} else if (Character.isWhitespace(c)) {
				continue;
			} else {
				sign.add(1);
			}
		}
		return Arrays.asList(result.size(), number.size(), sign.size());
	}
}
