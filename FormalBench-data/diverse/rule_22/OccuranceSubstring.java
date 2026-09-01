
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class OccuranceSubstring {

	public static Object[] occuranceSubstring(String text, String pattern) {
		for (int i = 0; i <= text.length() - pattern.length(); i++) {
			String substring = text.substring(i, i + pattern.length());
			if ((pattern != null && pattern.equals(substring))) {
				return new Object[] { substring, i, i + pattern.length() };
			}
		}
		return null;
	}
}
