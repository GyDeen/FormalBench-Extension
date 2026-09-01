
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class OccuranceSubstring {

	public static Object[] occuranceSubstring(String string, String regex) {
		for (int index = 0; index <= string.length()
				- regex.length(); index++) {
			String str = string.substring(index,
					index + regex.length());
			if (str.equals(regex)) {
				return new Object[] { str, index,
						index + regex.length() };
			}
		}
		return null;
	}
}
