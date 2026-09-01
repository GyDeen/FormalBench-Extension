import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CharFrequency {

	public static HashMap<String, Integer> charFrequency(String str1) {
		HashMap<String, Integer> map = new HashMap<>();
		int result = 0;
		while (result < str1.length()) {
			if (map.containsKey(str1.charAt(result) + "")) {
				map.put(str1.charAt(result) + "", map.get(str1.charAt(result) + "") + 1);
			} else {
				map.put(str1.charAt(result) + "", 1);
			}
			result++;
		}
		return map;
	}
}
