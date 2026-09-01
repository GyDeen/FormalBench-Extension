import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class IsIsomorphic {

	public static Boolean isIsomorphic(String string, String str) {
		HashMap<Character, Character> maps = new HashMap<>();
		for (int index = 0; index < string.length(); index++) {
			if (maps.containsKey(string.charAt(index))) {
				if (maps.get(string.charAt(index)) != str
						.charAt(index)) {
					return false;
				}
			} else {
				if (maps.containsValue(str.charAt(index))) {
					return false;
				} else {
					maps.put(string.charAt(index),
							str.charAt(index));
				}
			}
		}
		return true;
	}
}
