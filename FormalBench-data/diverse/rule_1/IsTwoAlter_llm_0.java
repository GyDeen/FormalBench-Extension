import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class IsTwoAlter {

	public static Boolean isTwoAlter(String string) {
		for (int n = 0; n < string.length() - 2; n++) {
			if (string.charAt(n) != string.charAt(n + 2)) {
				return false;
			}
		}

		if (string.charAt(0) == string.charAt(1))
			return false;

		return true;
	}
}
