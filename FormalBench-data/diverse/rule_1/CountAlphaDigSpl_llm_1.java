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
			char character = string.charAt(index);
			if (Character.isAlphabetic(character)) {
				result.add(1);
			} else if (Character.isDigit(character)) {
				number.add(1);
			} else if (Character.isWhitespace(character)) {
				continue;
			} else {
				sign.add(1);
			}
		}
		return Arrays.asList(result.size(), number.size(), sign.size());
	}
}
