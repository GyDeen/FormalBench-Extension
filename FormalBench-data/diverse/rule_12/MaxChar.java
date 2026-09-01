import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MaxChar {

	public static String maxChar(String str1) {
		String maxChar = "";
		int max = 0;
		HashMap<Character, Integer> map = new HashMap<>();
		for (int i = 0; i < str1.length(); i++) {
			char ch = str1.charAt(i);
			if (map.containsKey(ch)) {
				map.put(ch, map.get(ch) + 1);
			} else {
				map.put(ch, 1);
			}
		}
		for (Map.Entry<Character, Integer> entry : map.entrySet()) {
			if (entry.getValue() > max) {
				max = entry.getValue();
				maxChar = entry.getKey().toString();
			}
		}
		return maxChar;
	}
}
