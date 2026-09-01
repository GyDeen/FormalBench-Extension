import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class OddEquivalent {

	public static int oddEquivalent(String s, int n) {
		int count = 0;
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '0') {
				count++;
			} else {
				int c = count + 2;
				count = (c) % n;
			}
		}
		return count;
	}
}
