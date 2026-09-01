import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class IsTwoAlter {

	public static Boolean isTwoAlter(String text) {
		for (int n = 0; n < text.length() - 2; n++) {
			if (text.charAt(n) != text.charAt(n + 2)) {
				return false;
			}
		}

		if (text.charAt(0) == text.charAt(1))
			return false;

		return true;
	}
}
