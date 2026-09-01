
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class OccuranceSubstring {

	public static Object[] occuranceSubstring(String string, String regex) {
		for (int index = 0; index <= string.length()
				- regex.length(); index++) {
			String s = string.substring(index,
					index + regex.length());
			if (s.equals(regex)) {
				return new Object[] { s, index,
						index + regex.length() };
			}
		}
		return null;
	}
}
