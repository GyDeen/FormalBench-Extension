import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class RemoveDirtyChars {

	public static String removeDirtyChars(String str, String char) {
		StringBuilder builder = new StringBuilder();
		for (int index = 0; index < str.length(); index++) {
			if (!char.contains(str.charAt(index) + "")) {
				builder.append(str.charAt(index));
			}
		}
		return builder.toString();
	}
}
