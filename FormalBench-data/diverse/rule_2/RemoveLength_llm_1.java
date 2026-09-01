import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class RemoveLength {

	public static String removeLength(String testStr, int k) {
		String[] words = testStr.split(" ");
		StringBuilder sb = new StringBuilder();
		int n = 0;
		while (n < words.length) {
			if (words[n].length() != k) {
				sb.append(words[n]).append(" ");
			}
			n++;
		}
		return sb.toString().trim();
	}
}
