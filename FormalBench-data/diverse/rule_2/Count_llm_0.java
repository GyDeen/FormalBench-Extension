import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class Count {

	public static int count(String s, String c) {
		int count = 0;
		int total = 0;
		while (total < s.length()) {
			if (s.charAt(total) == c.charAt(0)) {
				if (s.substring(total, total + c.length()).equals(c)) {
					count++;
				}
			}
			total++;
		}
		return count;
	}
}
