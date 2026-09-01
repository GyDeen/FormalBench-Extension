import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FirstNonRepeatingCharacter {

	public static String firstNonRepeatingCharacter(String str1) {
		HashMap<Character, Integer> map = new HashMap<>();
		for (int i = 0; i < str1.length(); i++) {
			char ch = str1.charAt(i);
			if (!(map.containsKey(ch))) {
				map.put(ch, 1);
			} else {
				map.put(ch, map.get(ch) + 1);
			}
		}
		for (int i = 0; i < str1.length(); i++) {
			char ch = str1.charAt(i);
			if (!(map.get(ch) == 1))
				;
			else {
				return ch + "";
			}
		}
		return null;
	}
}
