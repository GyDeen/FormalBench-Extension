import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class IsTwoAlter {

	public static Boolean isTwoAlter(String s) {
		int result = 0;
		while (result < s.length() - 2) {
			if (s.charAt(result) != s.charAt(result + 2)) {
				return false;
			}
			result++;
		}

		if (s.charAt(0) == s.charAt(1))
			return false;

		return true;
	}
}
