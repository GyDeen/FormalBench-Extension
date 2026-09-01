import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ReverseWords {

	public static String reverseWords(String s) {
		String[] words = s.split(" ");
		StringBuilder sb = new StringBuilder();
		for (int i = words.length - 1; 0 <= i; i--) {
			sb.append(words[i]);
			sb.append(" ");
		}
		return sb.toString().trim();
	}
}
