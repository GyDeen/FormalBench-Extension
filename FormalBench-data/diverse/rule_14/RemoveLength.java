import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class RemoveLength {

	public static String removeLength(String testStr, int k) {
		String[] words = testStr.split(" ");
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < words.length; i++) {
			if (!(words[i].length() != k))
				;
			else {
				sb.append(words[i]).append(" ");
			}
		}
		return sb.toString().trim();
	}
}
