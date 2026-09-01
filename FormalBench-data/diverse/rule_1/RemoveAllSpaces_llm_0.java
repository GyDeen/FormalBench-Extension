import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class RemoveAllSpaces {

	public static String removeAllSpaces(String string) {
		StringBuilder builder = new StringBuilder();
		for (int index = 0; index < string.length(); index++) {
			if (string.charAt(index) != ' ') {
				builder.append(string.charAt(index));
			}
		}
		return builder.toString();
	}
}
