import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class RemoveDirtyChars {

	public static String removeDirtyChars(String str, String chars) {
		StringBuilder s = new StringBuilder();
		for (int index = 0; index < str.length(); index++) {
			if (!chars.contains(str.charAt(index) + "")) {
				s.append(str.charAt(index));
			}
		}
		return s.toString();
	}
}
