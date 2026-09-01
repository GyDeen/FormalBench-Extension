import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class IsTwoAlter {

	public static Boolean isTwoAlter(String s) {
		int ret = 0;
		while (ret < s.length() - 2) {
			if (s.charAt(ret) != s.charAt(ret + 2)) {
				return false;
			}
			ret++;
		}

		if (s.charAt(0) == s.charAt(1))
			return false;

		return true;
	}
}
