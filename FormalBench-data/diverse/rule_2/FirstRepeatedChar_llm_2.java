import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FirstRepeatedChar {

	public static String firstRepeatedChar(String str1) {
		HashMap<Character, Integer> map = new HashMap<>();
		int result = 0;
		while (result < str1.length()) {
			char ch = str1.charAt(result);
			if (map.containsKey(ch)) {
				return ch + "";
			} else {
				map.put(ch, 1);
			}
			result++;
		}
		return "None";
	}
}
