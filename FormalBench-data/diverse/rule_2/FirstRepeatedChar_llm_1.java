import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FirstRepeatedChar {

	public static String firstRepeatedChar(String str1) {
		HashMap<Character, Integer> map = new HashMap<>();
		int index = 0;
		while (index < str1.length()) {
			char ch = str1.charAt(index);
			if (map.containsKey(ch)) {
				return ch + "";
			} else {
				map.put(ch, 1);
			}
			index++;
		}
		return "None";
	}
}
