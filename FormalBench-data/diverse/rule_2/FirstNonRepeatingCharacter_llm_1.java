import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FirstNonRepeatingCharacter {

	public static String firstNonRepeatingCharacter(String str1) {
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
		int result = 0;
		while (result < str1.length()) {
			char ch = str1.charAt(result);
			if (map.get(ch) == 1) {
				return ch + "";
			}
			result++;
		}
		return null;
	}
}
