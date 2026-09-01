import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ReverseWords {

	public static String reverseWords(String s) {
		String[] words = s.split(" ");
		StringBuilder sb = new StringBuilder();
		int num = words.length - 1;
		while (num >= 0) {
			sb.append(words[num]);
			sb.append(" ");
			num--;
		}
		return sb.toString().trim();
	}
}
