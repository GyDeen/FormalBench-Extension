
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class OccuranceSubstring {

	public static Object[] occuranceSubstring(String text, String pattern) {
		int count = text.length() - pattern.length();
		for (int i = 0; i <= count; i++) {
			String substring = text.substring(i, i + pattern.length());
			if (substring.equals(pattern)) {
				return new Object[] { substring, i, i + pattern.length() };
			}
		}
		return null;
	}
}
