import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FirstRepeatedChar {

	public static String firstRepeatedChar(String string) {
		HashMap<Character, Integer> str = new HashMap<>();
		for (int c = 0; c < string.length(); c++) {
			char ele = string.charAt(c);
			if (str.containsKey(ele)) {
				return ele + "";
			} else {
				str.put(ele, 1);
			}
		}
		return "None";
	}
}
