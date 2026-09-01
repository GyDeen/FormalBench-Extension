import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class IsTwoAlter {

	public static Boolean isTwoAlter(String string) {
		for (int index = 0; index < string.length() - 2; index++) {
			if (string.charAt(index) != string.charAt(index + 2)) {
				return false;
			}
		}

		if (string.charAt(0) == string.charAt(1))
			return false;

		return true;
	}
}
