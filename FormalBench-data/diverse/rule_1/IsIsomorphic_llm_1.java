import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class IsIsomorphic {

	public static Boolean isIsomorphic(String string, String str) {
		HashMap<Character, Character> hash = new HashMap<>();
		for (int index = 0; index < string.length(); index++) {
			if (hash.containsKey(string.charAt(index))) {
				if (hash.get(string.charAt(index)) != str
						.charAt(index)) {
					return false;
				}
			} else {
				if (hash.containsValue(str.charAt(index))) {
					return false;
				} else {
					hash.put(string.charAt(index),
							str.charAt(index));
				}
			}
		}
		return true;
	}
}
