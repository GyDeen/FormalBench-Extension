import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class IsIsomorphic {

	public static Boolean isIsomorphic(String str1, String str2) {
		HashMap<Character, Character> map = new HashMap<>();
		int index = 0;
		while (index < str1.length()) {
			if (map.containsKey(str1.charAt(index))) {
				if (map.get(str1.charAt(index)) != str2.charAt(index)) {
					return false;
				}
			} else {
				if (map.containsValue(str2.charAt(index))) {
					return false;
				} else {
					map.put(str1.charAt(index), str2.charAt(index));
				}
			}
			index++;
		}
		return true;
	}
}
