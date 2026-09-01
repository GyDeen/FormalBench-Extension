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
		boolean temp = j >= 0;
		while (result && temp) {
			boolean remove = s.charAt(i) != ch.charAt(0);
			boolean tmp = s.charAt(j) != ch.charAt(0);
			boolean is = s.charAt(i) != ch.charAt(0);
			if (remove && tmp) {
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
