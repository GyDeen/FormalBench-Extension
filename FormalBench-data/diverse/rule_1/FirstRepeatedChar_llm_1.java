import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FirstRepeatedChar {

	public static String firstRepeatedChar(String string) {
		HashMap<Character, Integer> str = new HashMap<>();
		for (int index = 0; index < string.length(); index++) {
			char c = string.charAt(index);
			if (str.containsKey(c)) {
				return c + "";
			} else {
				str.put(c, 1);
			}
		}
		return "None";
	}
}
