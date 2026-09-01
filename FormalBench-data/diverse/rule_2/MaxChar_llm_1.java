import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MaxChar {

	public static String maxChar(String str1) {
		int max = 0;
		String maxChar = "";
		HashMap<Character, Integer> map = new HashMap<>();
		int result = 0;
		while (result < str1.length()) {
			char ch = str1.charAt(result);
			if (map.containsKey(ch)) {
				map.put(ch, map.get(ch) + 1);
			} else {
				map.put(ch, 1);
			}
			result++;
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
