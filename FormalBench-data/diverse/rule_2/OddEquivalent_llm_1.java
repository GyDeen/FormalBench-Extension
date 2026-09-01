import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class OddEquivalent {

	public static int oddEquivalent(String s, int n) {
		int count = 0;
		int offset = 0;
		while (offset < s.length()) {
			if (s.charAt(offset) == '0') {
				count++;
			} else {
				count = (count + 2) % n;
			}
			offset++;
		}
		return count;
	}
}
