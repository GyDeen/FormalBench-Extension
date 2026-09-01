import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CharFrequency {

	public static HashMap<String, Integer> charFrequency(String str1) {
		HashMap<String, Integer> map = new HashMap<>();
		int index = 0;
		while (index < str1.length()) {
			if (map.containsKey(str1.charAt(index) + "")) {
				map.put(str1.charAt(index) + "", map.get(str1.charAt(index) + "") + 1);
			} else {
				map.put(str1.charAt(index) + "", 1);
			}
			index++;
		}
		return map;
	}
}
