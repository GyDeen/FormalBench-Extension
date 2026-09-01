
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class OccuranceSubstring {

	public static Object[] occuranceSubstring(String text, String pattern) {
		int i = 0;
		while (i <= text.length() - pattern.length()) {
			String substring = text.substring(i, i + pattern.length());
			if (substring.equals(pattern)) {
				return new Object[] { substring, i, i + pattern.length() };
			}
			i++;
		}
		return null;
	}
}
