import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class Count {

	public static int count(String s, String c) {
		int count = 0;
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == c.charAt(0)) {
				if (s.substring(i, i + c.length()).equals(c)) {
					count += 1;
				}
			}
		}
		return count;
	}
}
