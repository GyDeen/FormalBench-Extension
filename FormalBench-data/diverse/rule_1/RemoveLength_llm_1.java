import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class RemoveLength {

	public static String removeLength(String str, int len) {
		String[] temp = str.split(" ");
		StringBuilder builder = new StringBuilder();
		for (int n = 0; n < temp.length; n++) {
			if (temp[n].length() != len) {
				builder.append(temp[n]).append(" ");
			}
		}
		return builder.toString().trim();
	}
}
