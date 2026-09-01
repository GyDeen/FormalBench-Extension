import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class OddEquivalent {

	public static int oddEquivalent(String s, int n) {
		int count = 0;
		int end = 0;
		while (end < s.length()) {
			if (s.charAt(end) == '0') {
				count++;
			} else {
				count = (count + 2) % n;
			}
			end++;
		}
		return count;
	}
}
