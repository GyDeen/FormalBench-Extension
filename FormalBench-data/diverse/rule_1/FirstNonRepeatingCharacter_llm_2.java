import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FirstNonRepeatingCharacter {

	public static String firstNonRepeatingCharacter(String text) {
		HashMap<Character, Integer> str = new HashMap<>();
		for (int i = 0; i < text.length(); i++) {
			char c = text.charAt(i);
			if (str.containsKey(c)) {
				str.put(c, str.get(c) + 1);
			} else {
				str.put(c, 1);
			}
		}
		for (int j = 0; j < text.length(); j++) {
			char c = text.charAt(j);
			if (str.get(c) == 1) {
				return c + "";
			}
		}
		return null;
	}
}
