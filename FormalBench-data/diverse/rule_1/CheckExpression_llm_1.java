import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CheckExpression {

	public static Boolean checkExpression(String expression) {
		Stack<Character> characters = new Stack<>();
		for (int index = 0; index < expression.length(); index++) {
			char c = expression.charAt(index);
			if (c == '{' || c == '(' || c == '[') {
				characters.push(c);
			} else if (c == '}' || c == ')' || c == ']') {
				if (characters.isEmpty()) {
					return false;
				}
				char code = characters.pop();
				if (code == '{' && c != '}') {
					return false;
				}
				if (code == '(' && c != ')') {
					return false;
				}
				if (code == '[' && c != ']') {
					return false;
				}
			}
		}
		return characters.isEmpty();
	}
}
