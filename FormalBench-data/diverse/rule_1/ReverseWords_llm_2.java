import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ReverseWords {

	public static String reverseWords(String string) {
		String[] r = string.split(" ");
		StringBuilder buffer = new StringBuilder();
		for (int n = r.length - 1; n >= 0; n--) {
			buffer.append(r[n]);
			buffer.append(" ");
		}
		return buffer.toString().trim();
	}
}
