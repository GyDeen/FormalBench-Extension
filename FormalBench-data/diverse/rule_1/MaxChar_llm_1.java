import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MaxChar {

	public static String maxChar(String string) {
		int ret = 0;
		String maxChar = "";
		HashMap<Character, Integer> maps = new HashMap<>();
		for (int c = 0; c < string.length(); c++) {
			char ch = string.charAt(c);
			if (maps.containsKey(ch)) {
				maps.put(ch, maps.get(ch) + 1);
			} else {
				maps.put(ch, 1);
			}
		}
		for (Map.Entry<Character, Integer> entries : maps.entrySet()) {
			if (entries.getValue() > ret) {
				ret = entries.getValue();
				maxChar = entries.getKey().toString();
			}
		}
		return maxChar;
	}
}
