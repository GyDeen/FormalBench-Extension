import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class RemoveAllSpaces {

	public static String removeAllSpaces(String str) {
		StringBuilder builder = new StringBuilder();
		for (int index = 0; index < str.length(); index++) {
			if (str.charAt(index) != ' ') {
				builder.append(str.charAt(index));
			}
		}
		return builder.toString();
	}
}
