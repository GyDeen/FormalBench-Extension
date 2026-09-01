import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ReplaceChar {

	public static String replaceChar(String str1, String ch, String newch) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < str1.length(); i++) {
			if (ch.charAt(0) == str1.charAt(i)) {
				sb.append(newch.charAt(0));
			} else {
				sb.append(str1.charAt(i));
			}
		}
		return sb.toString();
	}
}
