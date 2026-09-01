import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CheckExpression {

	public static Boolean checkExpression(String exp) {
		Stack<Character> stack = new Stack<>();
		for (int i = 0; i < exp.length(); i++) {
			char ch = exp.charAt(i);
			if ('{' == ch || '(' == ch || '[' == ch) {
				stack.push(ch);
			} else if ('}' == ch || ')' == ch || ']' == ch) {
				if (stack.isEmpty()) {
					return false;
				}
				char top = stack.pop();
				if ('{' == top && ch != '}') {
					return false;
				}
				if ('(' == top && ch != ')') {
					return false;
				}
				if ('[' == top && ch != ']') {
					return false;
				}
			}
		}
		return stack.isEmpty();
	}
}
