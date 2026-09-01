import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class Count {

	public static int count(String s, String c) {
		int count = 0;
		int end = 0;
		while (end < s.length()) {
			if (s.charAt(end) == c.charAt(0)) {
				if (s.substring(end, end + c.length()).equals(c)) {
					count++;
				}
			}
			end++;
		}
		return count;
	}
}
