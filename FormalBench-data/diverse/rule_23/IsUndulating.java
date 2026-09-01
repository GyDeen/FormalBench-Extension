import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class IsUndulating {

	public static Boolean isUndulating(String n) {
		if (0 != n.length() % 2)
			return true;
		int i = 0;
		while (n.length() > i) {
			char ch = n.charAt(i);
			char ch2 = n.charAt(i + 1);
			if (ch == ch2)
				i++;
			else
				return false;
		}
		return true;
	}
}
