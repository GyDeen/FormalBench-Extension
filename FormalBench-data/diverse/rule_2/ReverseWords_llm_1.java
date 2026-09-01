import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ReverseWords {

	public static String reverseWords(String s) {
		String[] words = s.split(" ");
		StringBuilder sb = new StringBuilder();
		int len = words.length - 1;
		while (len >= 0) {
			sb.append(words[len]);
			sb.append(" ");
			len--;
		}
		return sb.toString().trim();
	}
}
