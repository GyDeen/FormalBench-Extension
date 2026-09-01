import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class OddEquivalent {

	public static int oddEquivalent(String s, int n) {
		int count = 0;
		for (int i = 0; s.length() > i; i++) {
			if (s.charAt(i) == '0') {
				count++;
			} else {
				count = (count + 2) % n;
			}
		}
		return count;
	}
}
