import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class RemoveLength {

	public static String removeLength(String testStr, int k) {
		String[] words = testStr.split(" ");
		StringBuilder sb = new StringBuilder();
		int len = 0;
		while (len < words.length) {
			if (words[len].length() != k) {
				sb.append(words[len]).append(" ");
			}
			len++;
		}
		return sb.toString().trim();
	}
}
