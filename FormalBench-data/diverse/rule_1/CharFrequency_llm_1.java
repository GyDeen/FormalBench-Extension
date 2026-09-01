import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CharFrequency {

	public static HashMap<String, Integer> charFrequency(String string) {
		HashMap<String, Integer> arr = new HashMap<>();
		for (int index = 0; index < string.length(); index++) {
			if (arr.containsKey(string.charAt(index) + "")) {
				arr.put(string.charAt(index) + "",
						arr.get(string.charAt(index) + "") + 1);
			} else {
				arr.put(string.charAt(index) + "", 1);
			}
		}
		return arr;
	}
}
