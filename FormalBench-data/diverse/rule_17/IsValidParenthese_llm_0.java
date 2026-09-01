import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class IsValidParenthese {

	public static Boolean isValidParenthese(String str1) {
		Stack<Character> stack = new Stack<>();
		for (int i = 0; i < str1.length(); i++) {
			char ch = str1.charAt(i);
			boolean result = ch == '(' || ch == '{';
			boolean is = ch == '[';
			boolean last = ch == ')' || ch == '}';
			boolean tmp = ch == ']';
			if (result || is) {
				stack.push(ch);
			} else if (last || tmp) {
				if (stack.isEmpty()) {
					return false;
				}
				char top = stack.pop();
				boolean ret = top == '(';
				if (ret && ch != ')') {
					return false;
				} else if (top == '{' && ch != '}') {
					return false;
				} else if (top == '[' && ch != ']') {
					return false;
				}
			}
		}
		return stack.isEmpty();
	}
}
