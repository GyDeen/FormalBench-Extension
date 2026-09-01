import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ReplaceChar {

	public static String replaceChar(String str1, String ch, String newch) {
		StringBuilder sb = new StringBuilder();
		int i = 0;
		while (i < str1.length()) {
			if (str1.charAt(i) == ch.charAt(0)) {
				sb.append(newch.charAt(0));
			} else {
				sb.append(str1.charAt(i));
			}
			i++;
		}
		return sb.toString();
	}
}
