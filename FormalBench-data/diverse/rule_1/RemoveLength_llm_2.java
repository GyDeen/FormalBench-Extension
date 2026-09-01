import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class RemoveLength {

	public static String removeLength(String string, int len) {
		String[] arr = string.split(" ");
		StringBuilder builder = new StringBuilder();
		for (int n = 0; n < arr.length; n++) {
			if (arr[n].length() != len) {
				builder.append(arr[n]).append(" ");
			}
		}
		return builder.toString().trim();
	}
}
