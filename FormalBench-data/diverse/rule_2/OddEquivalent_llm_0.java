import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class OddEquivalent {

	public static int oddEquivalent(String s, int n) {
		int count = 0;
		int result = 0;
		while (result < s.length()) {
			if (s.charAt(result) == '0') {
				count++;
			} else {
				count = (count + 2) % n;
			}
			result++;
		}
		return count;
	}
}
