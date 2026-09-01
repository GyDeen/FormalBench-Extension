import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CountHexadecimal {

	public static int countHexadecimal(int value, int r) {
		int result = 0;
		for (int i = value; i <= r; i++) {
			String s = Integer.toHexString(i);
			int n = s.length();
			for (int j = 0; j < n; j++) {
				char k = s.charAt(j);
				if (k >= 'A' && k <= 'F') {
					result++;
				} else if (k >= 'a' && k <= 'f') {
					result++;
				}
			}
		}
		return result;
	}
}
