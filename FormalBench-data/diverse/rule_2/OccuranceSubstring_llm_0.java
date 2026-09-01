
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class OccuranceSubstring {

	public static Object[] occuranceSubstring(String text, String pattern) {
		int result = 0;
		while (result <= text.length() - pattern.length()) {
			String substring = text.substring(result, result + pattern.length());
			if (substring.equals(pattern)) {
				return new Object[] { substring, result, result + pattern.length() };
			}
			result++;
		}
		return null;
	}
}
