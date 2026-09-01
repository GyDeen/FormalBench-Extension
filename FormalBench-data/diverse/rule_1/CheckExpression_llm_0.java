import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CheckExpression {

	public static Boolean checkExpression(String expression) {
		Stack<Character> characters = new Stack<>();
		for (int index = 0; index < expression.length(); index++) {
			char character = expression.charAt(index);
			if (character == '{' || character == '(' || character == '[') {
				characters.push(character);
			} else if (character == '}' || character == ')' || character == ']') {
				if (characters.isEmpty()) {
					return false;
				}
				char c = characters.pop();
				if (c == '{' && character != '}') {
					return false;
				}
				if (c == '(' && character != ')') {
					return false;
				}
				if (c == '[' && character != ']') {
					return false;
				}
			}
		}
		return characters.isEmpty();
	}
}
