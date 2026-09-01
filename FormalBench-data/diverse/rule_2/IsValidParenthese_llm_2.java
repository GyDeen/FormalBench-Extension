import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class IsValidParenthese {

	public static Boolean isValidParenthese(String str1) {
		Stack<Character> stack = new Stack<>();
		int i = 0;
		while (i < str1.length()) {
			char ch = str1.charAt(i);
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
			i++;
		}
		return stack.isEmpty();
	}
}
