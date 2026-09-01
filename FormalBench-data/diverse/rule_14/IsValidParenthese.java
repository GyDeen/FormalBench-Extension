import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class IsValidParenthese {

	public static Boolean isValidParenthese(String str1) {
		Stack<Character> stack = new Stack<>();
		for (int i = 0; i < str1.length(); i++) {
			char ch = str1.charAt(i);
			if (!(ch == '(' || ch == '{' || ch == '[')) {
				if (ch == ')' || ch == '}' || ch == ']') {
					if (stack.isEmpty()) {
						return false;
					}
					char top = stack.pop();
					if (top == '(' && ch != ')') {
						return false;
					} else if (top == '{' && ch != '}') {
						return false;
					} else if (top == '[' && ch != ']') {
						return false;
					}
				}
			} else {
				stack.push(ch);
			}
		}
		return stack.isEmpty();
	}
}
