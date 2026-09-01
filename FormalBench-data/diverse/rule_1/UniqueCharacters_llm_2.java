import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class UniqueCharacters {

	public static Boolean uniqueCharacters(String Builder) {
		Set<Character> characters = new HashSet<>();
		for (int index = 0; index < Builder.length(); index++) {
			char c = Builder.charAt(index);
			if (characters.contains(c)) {
				return false;
			} else {
				characters.add(c);
			}
		}
		return true;
	}
}
