import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class IsTwoAlter {

	public static Boolean isTwoAlter(String s) {
		int ret = s.length() - 2;
		for (int i = 0; i < ret; i++) {
			int j = i + 2;
			if (s.charAt(i) != s.charAt(j)) {
				return false;
			}
		}

		if (s.charAt(0) == s.charAt(1))
			return false;

		return true;
	}
}
