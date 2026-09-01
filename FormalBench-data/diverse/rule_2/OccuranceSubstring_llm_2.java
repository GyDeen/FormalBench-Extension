
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class OccuranceSubstring {

	public static Object[] occuranceSubstring(String text, String pattern) {
		int count = 0;
		while (count <= text.length() - pattern.length()) {
			String substring = text.substring(count, count + pattern.length());
			if (substring.equals(pattern)) {
				return new Object[] { substring, count, count + pattern.length() };
			}
			count++;
		}
		return null;
	}
}
