
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class OccuranceSubstring {

	public static Object[] occuranceSubstring(String str, String regex) {
		for (int index = 0; index <= str.length()
				- regex.length(); index++) {
			String string = str.substring(index,
					index + regex.length());
			if (string.equals(regex)) {
				return new Object[] { string, index,
						index + regex.length() };
			}
		}
		return null;
	}
}
