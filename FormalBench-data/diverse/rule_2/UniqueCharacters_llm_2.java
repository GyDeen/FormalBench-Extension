import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class UniqueCharacters {

	public static Boolean uniqueCharacters(String str) {
		Set<Character> set = new HashSet<>();
		int index = 0;
		while (index < str.length()) {
			char ch = str.charAt(index);
			if (set.contains(ch)) {
				return false;
			} else {
				set.add(ch);
			}
			index++;
		}
		return true;
	}
}
