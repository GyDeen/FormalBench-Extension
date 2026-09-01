import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CharFrequency {

	public static HashMap<String, Integer> charFrequency(String input) {
		HashMap<String, Integer> str = new HashMap<>();
		for (int index = 0; index < input.length(); index++) {
			if (str.containsKey(input.charAt(index) + "")) {
				str.put(input.charAt(index) + "",
						str.get(input.charAt(index) + "") + 1);
			} else {
				str.put(input.charAt(index) + "", 1);
			}
		}
		return str;
	}
}
