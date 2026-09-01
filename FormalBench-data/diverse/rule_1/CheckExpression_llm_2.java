import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CheckExpression {

	public static Boolean checkExpression(String expression) {
		Stack<Character> chars = new Stack<>();
		for (int index = 0; index < expression.length(); index++) {
			char character = expression.charAt(index);
			if (character == '{' || character == '(' || character == '[') {
				chars.push(character);
			} else if (character == '}' || character == ')' || character == ']') {
				if (chars.isEmpty()) {
					return false;
				}
				char c = chars.pop();
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
		return chars.isEmpty();
	}
}
