import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ReverseWords {

	public static String reverseWords(String s) {
		String[] words = s.split(" ");
		StringBuilder sb = new StringBuilder();
		int n = words.length - 1;
		while (n >= 0) {
			sb.append(words[n]);
			sb.append(" ");
			n--;
		}
		return sb.toString().trim();
	}
}
