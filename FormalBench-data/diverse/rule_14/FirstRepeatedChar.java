import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FirstRepeatedChar {

	public static String firstRepeatedChar(String str1) {
		HashMap<Character, Integer> map = new HashMap<>();
		for (int i = 0; i < str1.length(); i++) {
			char ch = str1.charAt(i);
			if (!(map.containsKey(ch))) {
				map.put(ch, 1);
			} else {
				return ch + "";
			}
		}
		return "None";
	}
}
