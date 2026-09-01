import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class IsTwoAlter {

	public static Boolean isTwoAlter(String s) {
		int result = s.length() - 2;
		for (int i = 0; i < result; i++) {
			int tmp = i + 2;
			if (s.charAt(i) != s.charAt(tmp)) {
				return false;
			}
		}

		if (s.charAt(0) == s.charAt(1))
			return false;

		return true;
	}
}
