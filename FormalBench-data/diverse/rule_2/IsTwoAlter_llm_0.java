import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class IsTwoAlter {

	public static Boolean isTwoAlter(String s) {
		int i = 0;
		while (i < s.length() - 2) {
			if (s.charAt(i) != s.charAt(i + 2)) {
				return false;
			}
			i++;
		}

		if (s.charAt(0) == s.charAt(1))
			return false;

		return true;
	}
}
