import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FirstNonRepeatingCharacter {

	public static String firstNonRepeatingCharacter(String string) {
		HashMap<Character, Integer> str = new HashMap<>();
		for (int i = 0; i < string.length(); i++) {
			char c = string.charAt(i);
			if (str.containsKey(c)) {
				str.put(c, str.get(c) + 1);
			} else {
				str.put(c, 1);
			}
		}
		for (int j = 0; j < string.length(); j++) {
			char c = string.charAt(j);
			if (str.get(c) == 1) {
				return c + "";
			}
		}
		return null;
	}
}
