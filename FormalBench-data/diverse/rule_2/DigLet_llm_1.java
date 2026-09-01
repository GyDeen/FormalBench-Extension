
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class DigLet {

	public static int[] digLet(String s) {
		int letterCount = 0;
		int digitCount = 0;

		int exponent = 0;
		while (exponent < s.length()) {
			char c = s.charAt(exponent);
			if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')) {
				letterCount++;
			}
			if (c >= '0' && c <= '9') {
				digitCount++;
			}
			exponent++;
		}

		return new int[] { letterCount, digitCount };
	}
}
