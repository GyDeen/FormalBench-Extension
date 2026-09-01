import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class UniqueCharacters {

	public static Boolean uniqueCharacters(String str) {
		Set<Character> set = new HashSet<>();
		int len = 0;
		while (len < str.length()) {
			char ch = str.charAt(len);
			if (set.contains(ch)) {
				return false;
			} else {
				set.add(ch);
			}
			len++;
		}
		return true;
	}
}
