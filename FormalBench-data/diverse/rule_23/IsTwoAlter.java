import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class IsTwoAlter {

	public static Boolean isTwoAlter(String s) {
		for (int i = 0; s.length() - 2 > i; i++) {
			if (s.charAt(i + 2) != s.charAt(i)) {
				return false;
			}
		}

		if (s.charAt(0) == s.charAt(1))
			return false;

		return true;
	}
}
