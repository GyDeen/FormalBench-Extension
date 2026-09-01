
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class OccuranceSubstring {

	public static Object[] occuranceSubstring(String text, String pattern) {
		for (int i = 0; text.length() - pattern.length() >= i; i++) {
			String substring = text.substring(i, i + pattern.length());
			if (substring.equals(pattern)) {
				return new Object[] { substring, i, i + pattern.length() };
			}
		}
		return null;
	}
}
