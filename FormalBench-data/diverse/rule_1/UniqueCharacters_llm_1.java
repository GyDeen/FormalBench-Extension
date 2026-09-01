import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class UniqueCharacters {

	public static Boolean uniqueCharacters(String string) {
		Set<Character> characters = new HashSet<>();
		for (int index = 0; index < string.length(); index++) {
			char character = string.charAt(index);
			if (characters.contains(character)) {
				return false;
			} else {
				characters.add(character);
			}
		}
		return true;
	}
}
