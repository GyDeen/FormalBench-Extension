import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class IsValidParenthese {

	public static Boolean isValidParenthese(String str1) {
		Stack<Character> stack = new Stack<>();
		int index = 0;
		while (index < str1.length()) {
			char ch = str1.charAt(index);
			if (ch == '(' || ch == '{' || ch == '[') {
				stack.push(ch);
			} else if (ch == ')' || ch == '}' || ch == ']') {
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
			index++;
		}
		return stack.isEmpty();
	}
}
