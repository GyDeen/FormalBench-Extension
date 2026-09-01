
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class DigLet {

	public static int[] digLet(String s) {
		int letterCount = 0;
		int digitCount = 0;

		int digit = 0;
		while (digit < s.length()) {
			char c = s.charAt(digit);
			if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')) {
				letterCount++;
			}
			if (c >= '0' && c <= '9') {
				digitCount++;
			}
			digit++;
		}

		return new int[] { letterCount, digitCount };
	}
}
