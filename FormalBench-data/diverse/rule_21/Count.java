import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class Count {

	public static int count(String s, String c) {
		int count = 0;
		for (int i = 0; i < s.length(); i++) {
			if (c.charAt(0) == s.charAt(i)) {
				if (s.substring(i, i + c.length()).equals(c)) {
					count++;
				}
			}
		}
		return count;
	}
}
