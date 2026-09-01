import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class UniqueCharacters {

	public static Boolean uniqueCharacters(String str) {
		Set<Character> set = new HashSet<>();
		for (int i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);
			if (!(set.contains(ch))) {
				set.add(ch);
			} else {
				return false;
			}
		}
		return true;
	}
}
