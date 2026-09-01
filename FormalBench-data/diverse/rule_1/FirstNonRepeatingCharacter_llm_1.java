import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FirstNonRepeatingCharacter {

	public static String firstNonRepeatingCharacter(String string) {
		HashMap<Character, Integer> maps = new HashMap<>();
		for (int i = 0; i < string.length(); i++) {
			char c = string.charAt(i);
			if (maps.containsKey(c)) {
				maps.put(c, maps.get(c) + 1);
			} else {
				maps.put(c, 1);
			}
		}
		for (int j = 0; j < string.length(); j++) {
			char c = string.charAt(j);
			if (maps.get(c) == 1) {
				return c + "";
			}
		}
		return null;
	}
}
