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
			char character = string.charAt(index);
			char c = string.charAt(index + 1);
			if (character == c)
				index++;
			else
				return false;
		}
		return true;
	}
}
