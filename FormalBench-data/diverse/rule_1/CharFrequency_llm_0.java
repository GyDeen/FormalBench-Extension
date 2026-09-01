import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CharFrequency {

	public static HashMap<String, Integer> charFrequency(String string) {
		HashMap<String, Integer> str = new HashMap<>();
		for (int index = 0; index < string.length(); index++) {
			if (str.containsKey(string.charAt(index) + "")) {
				str.put(string.charAt(index) + "",
						str.get(string.charAt(index) + "") + 1);
			} else {
				str.put(string.charAt(index) + "", 1);
			}
		}
		return str;
	}
}
