import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class IsValidParenthese {

	public static Boolean isValidParenthese(String parent) {
		Stack<Character> arr = new Stack<>();
		for (int index = 0; index < parent.length(); index++) {
			char c = parent.charAt(index);
			if (c == '(' || c == '{' || c == '[') {
				arr.push(c);
			} else if (c == ')' || c == '}' || c == ']') {
				if (arr.isEmpty()) {
					return false;
				}
				char last = arr.pop();
				if (last == '(' && c != ')') {
					return false;
				} else if (last == '{' && c != '}') {
					return false;
				} else if (last == '[' && c != ']') {
					return false;
				}
			}
		}
		return arr.isEmpty();
	}
}
