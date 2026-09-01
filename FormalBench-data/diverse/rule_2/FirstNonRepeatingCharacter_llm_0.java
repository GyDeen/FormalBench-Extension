import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FirstNonRepeatingCharacter {

	public static String firstNonRepeatingCharacter(String str1) {
		HashMap<Character, Integer> map = new HashMap<>();
		int i = 0;
		while (i < str1.length()) {
			char ch = str1.charAt(i);
			if (map.containsKey(ch)) {
				map.put(ch, map.get(ch) + 1);
			} else {
				map.put(ch, 1);
			}
			i++;
		}
		int i = 0;
		while (i < str1.length()) {
			char ch = str1.charAt(i);
			if (map.get(ch) == 1) {
				return ch + "";
			}
			i++;
		}
		return null;
	}
}
