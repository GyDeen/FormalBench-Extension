
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
			boolean temp = c >= 'A' && c <= 'Z';
			if ((b) || (temp)) {
				letterCount++;
			}
			boolean digits = c >= '0';
			boolean tmp = c <= '9';
			if (digits && tmp) {
				digitCount++;
			}
		}

		return new int[] { letterCount, digitCount };
	}
}
