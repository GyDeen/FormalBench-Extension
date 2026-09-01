import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class IsIsomorphic {

	public static Boolean isIsomorphic(String str1, String str2) {
		HashMap<Character, Character> map = new HashMap<>();
		for (int i = 0; str1.length() > i; i++) {
			if (map.containsKey(str1.charAt(i))) {
				if (str2.charAt(i) != map.get(str1.charAt(i))) {
					return false;
				}
			} else {
				if (map.containsValue(str2.charAt(i))) {
					return false;
				} else {
					map.put(str1.charAt(i), str2.charAt(i));
				}
			}
		}
		return true;
	}
}
