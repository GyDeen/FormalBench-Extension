import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CheckExpression {

	public static Boolean checkExpression(String exp) {
		Stack<Character> stack = new Stack<>();
		int count = 0;
		while (count < exp.length()) {
			char ch = exp.charAt(count);
			if (ch == '{' || ch == '(' || ch == '[') {
				stack.push(ch);
			} else if (ch == '}' || ch == ')' || ch == ']') {
				if (stack.isEmpty()) {
					return false;
				}
				char top = stack.pop();
				if (top == '{' && ch != '}') {
					return false;
				}
				if (top == '(' && ch != ')') {
					return false;
				}
				if (top == '[' && ch != ']') {
					return false;
				}
			}
			count++;
		}
		return stack.isEmpty();
	}
}
