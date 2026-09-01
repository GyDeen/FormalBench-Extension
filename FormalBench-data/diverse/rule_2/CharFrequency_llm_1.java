import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CharFrequency {

	public static HashMap<String, Integer> charFrequency(String str1) {
		HashMap<String, Integer> map = new HashMap<>();
		int num = 0;
		while (num < str1.length()) {
			if (map.containsKey(str1.charAt(num) + "")) {
				map.put(str1.charAt(num) + "", map.get(str1.charAt(num) + "") + 1);
			} else {
				map.put(str1.charAt(num) + "", 1);
			}
			num++;
		}
		return map;
	}
}
