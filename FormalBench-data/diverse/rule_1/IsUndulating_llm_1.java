import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class IsUndulating {

	public static Boolean isUndulating(String string) {
		if (string.length() % 2 != 0)
			return true;
		int index = 0;
		while (index < string.length()) {
			char c = string.charAt(index);
			char b = string.charAt(index + 1);
			if (c == b)
				index++;
			else
				return false;
		}
		return true;
	}
}
