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
				int num = count + 2;
				count = (num) % n;
			}
		}
		return count;
	}
}
