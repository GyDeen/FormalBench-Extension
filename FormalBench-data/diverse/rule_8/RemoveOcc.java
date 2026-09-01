import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class RemoveOcc {

	public static String removeOcc(String s, String ch) {
		StringBuilder sb = new StringBuilder();
		int i = 0;
		int j = s.length() - 1;
		while (i < s.length() && j >= 0) {
			if (s.charAt(i) != ch.charAt(0) && s.charAt(j) != ch.charAt(0)) {
				sb.append(s.charAt(i));
				i += 1;
			} else if (s.charAt(i) != ch.charAt(0) && s.charAt(j) == ch.charAt(0)) {
				i += 1;
			} else if (s.charAt(i) == ch.charAt(0) && s.charAt(j) != ch.charAt(0)) {
				j -= 1;
			} else {
				i += 1;
				j -= 1;
			}
		}
		return sb.toString();
	}
}
