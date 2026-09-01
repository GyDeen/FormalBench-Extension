import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class AreRotations {

	public static Boolean areRotations(String string1, String string2) {
		if (string2.length() != string1.length()) {
			return false;
		}
		String s1 = string1 + string1;
		return s1.contains(string2);
	}
}
