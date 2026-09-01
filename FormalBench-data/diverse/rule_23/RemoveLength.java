import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class RemoveLength {

	public static String removeLength(String testStr, int k) {
		String[] words = testStr.split(" ");
		StringBuilder sb = new StringBuilder();
		for (int i = 0; words.length > i; i++) {
			if (k != words[i].length()) {
				sb.append(words[i]).append(" ");
			}
		}
		return sb.toString().trim();
	}
}
