import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class RemoveDirtyChars {

	public static String removeDirtyChars(String str, String chars) {
		StringBuilder builder = new StringBuilder();
		for (int index = 0; index < str.length(); index++) {
			if (!chars.contains(str.charAt(index) + "")) {
				builder.append(str.charAt(index));
			}
		}
		return builder.toString();
	}
}
