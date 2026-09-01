import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FirstNonRepeatingCharacter {

	public static String firstNonRepeatingCharacter(String str1) {
		HashMap<Character, Integer> map = new HashMap<>();
		int index = 0;
		while (index < str1.length()) {
			char ch = str1.charAt(index);
			if (map.containsKey(ch)) {
				map.put(ch, map.get(ch) + 1);
			} else {
				map.put(ch, 1);
			}
			index++;
		}
		int index = 0;
		while (index < str1.length()) {
			char ch = str1.charAt(index);
			if (map.get(ch) == 1) {
				return ch + "";
			}
			index++;
		}
		return null;
	}
}
