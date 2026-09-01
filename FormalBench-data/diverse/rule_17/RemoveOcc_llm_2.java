import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class RemoveOcc {

	public static String removeOcc(String s, String ch) {
		StringBuilder sb = new StringBuilder();
		int i = 0;
		int j = s.length() - 1;
		boolean result = i < s.length();
		boolean tmp = j >= 0;
		while (result && tmp) {
			boolean remove = s.charAt(i) != ch.charAt(0);
			boolean prev = s.charAt(j) != ch.charAt(0);
			boolean is = s.charAt(i) != ch.charAt(0);
			if (remove && prev) {
				sb.append(s.charAt(i));
				i++;
			} else if (is && s.charAt(j) == ch.charAt(0)) {
				i++;
			} else if (s.charAt(i) == ch.charAt(0) && s.charAt(j) != ch.charAt(0)) {
				j--;
			} else {
				i++;
				j--;
			}
		}
		return sb.toString();
	}
}
