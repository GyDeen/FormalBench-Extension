import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class IsIsomorphic {

	public static Boolean isIsomorphic(String path, String str) {
		HashMap<Character, Character> maps = new HashMap<>();
		for (int index = 0; index < path.length(); index++) {
			if (maps.containsKey(path.charAt(index))) {
				if (maps.get(path.charAt(index)) != str
						.charAt(index)) {
					return false;
				}
			} else {
				if (maps.containsValue(str.charAt(index))) {
					return false;
				} else {
					maps.put(path.charAt(index),
							str.charAt(index));
				}
			}
		}
		return true;
	}
}
