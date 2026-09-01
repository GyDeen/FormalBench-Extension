import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FirstRepeatedChar {

	public static String firstRepeatedChar(String string) {
		HashMap<Character, Integer> maps = new HashMap<>();
		for (int c = 0; c < string.length(); c++) {
			char ele = string.charAt(c);
			if (maps.containsKey(ele)) {
				return ele + "";
			} else {
				maps.put(ele, 1);
			}
		}
		return "None";
	}
}
