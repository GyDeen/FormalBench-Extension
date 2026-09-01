import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class IsIsomorphic {

	public static Boolean isIsomorphic(String str1, String str2) {
		HashMap<Character, Character> map = new HashMap<>();
		int id = 0;
		while (id < str1.length()) {
			if (map.containsKey(str1.charAt(id))) {
				if (map.get(str1.charAt(id)) != str2.charAt(id)) {
					return false;
				}
			} else {
				if (map.containsValue(str2.charAt(id))) {
					return false;
				} else {
					map.put(str1.charAt(id), str2.charAt(id));
				}
			}
			id++;
		}
		return true;
	}
}
