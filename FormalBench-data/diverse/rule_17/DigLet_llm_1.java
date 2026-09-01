
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class DigLet {

	public static int[] digLet(String s) {
		int letterCount = 0;
		int digitCount = 0;

		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			boolean b = c >= 'a' && c <= 'z';
			boolean tmp = c >= 'A' && c <= 'Z';
			if ((b) || (tmp)) {
				letterCount++;
			}
			boolean digit = c >= '0';
			boolean temp = c <= '9';
			if (digit && temp) {
				digitCount++;
			}
		}

		return new int[] { letterCount, digitCount };
	}
}
