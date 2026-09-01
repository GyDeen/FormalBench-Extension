import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class IsValidParenthese {

	public static Boolean isValidParenthese(String parent) {
		Stack<Character> characters = new Stack<>();
		for (int index = 0; index < parent.length(); index++) {
			char character = parent.charAt(index);
			if (character == '(' || character == '{' || character == '[') {
				characters.push(character);
			} else if (character == ')' || character == '}' || character == ']') {
				if (characters.isEmpty()) {
					return false;
				}
				char c = characters.pop();
				if (c == '(' && character != ')') {
					return false;
				} else if (c == '{' && character != '}') {
					return false;
				} else if (c == '[' && character != ']') {
					return false;
				}
			}
		}
		return characters.isEmpty();
	}
}
