import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class RemoveLength {

	public static String removeLength(String testStr, int k) {
		String[] words = testStr.split(" ");
		StringBuilder sb = new StringBuilder();
		int i = 0;
		while (i < words.length) {
			if (words[i].length() != k) {
				sb.append(words[i]).append(" ");
			}
			i++;
		}
		return sb.toString().trim();
	}
}
