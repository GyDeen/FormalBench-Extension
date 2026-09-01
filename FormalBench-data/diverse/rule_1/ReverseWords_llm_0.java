import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ReverseWords {

	public static String reverseWords(String string) {
		String[] r = string.split(" ");
		StringBuilder builder = new StringBuilder();
		for (int n = r.length - 1; n >= 0; n--) {
			builder.append(r[n]);
			builder.append(" ");
		}
		return builder.toString().trim();
	}
}
