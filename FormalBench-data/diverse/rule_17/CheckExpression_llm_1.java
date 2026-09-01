import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CheckExpression {

	public static Boolean checkExpression(String exp) {
		Stack<Character> stack = new Stack<>();
		for (int i = 0; i < exp.length(); i++) {
			char ch = exp.charAt(i);
			boolean check = ch == '{' || ch == '(';
			boolean tmp = ch == '[';
			boolean ele = ch == '}' || ch == ')';
			boolean last = ch == ']';
			if (check || tmp) {
				stack.push(ch);
			} else if (ele || last) {
				if (stack.isEmpty()) {
					return false;
				}
				char top = stack.pop();
				boolean ret = top == '{';
				if (ret && ch != '}') {
					return false;
				}
				if (top == '(' && ch != ')') {
					return false;
				}
				if (top == '[' && ch != ']') {
					return false;
				}
			}
		}
		return stack.isEmpty();
	}
}
