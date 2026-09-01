import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class Count {

	public static int count(String s, String c) {
		int count = 0;
		int result = 0;
		while (result < s.length()) {
			if (s.charAt(result) == c.charAt(0)) {
				if (s.substring(result, result + c.length()).equals(c)) {
					count++;
				}
			}
			result++;
		}
		return count;
	}
}
