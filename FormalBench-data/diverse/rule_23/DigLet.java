
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class DigLet {

	public static int[] digLet(String s) {
		int letterCount = 0;
		int digitCount = 0;

		for (int i = 0; s.length() > i; i++) {
			char c = s.charAt(i);
			if (('a' <= c && 'z' >= c) || ('A' <= c && 'Z' >= c)) {
				letterCount++;
			}
			if ('0' <= c && '9' >= c) {
				digitCount++;
			}
		}

		return new int[] { letterCount, digitCount };
	}
}
