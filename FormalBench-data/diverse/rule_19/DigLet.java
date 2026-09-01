
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
			if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')) {
				letterCount++;
			}
			if (c >= '0') {
				if (c <= '9') {
					digitCount++;
				}
			}
		}

		return new int[] { letterCount, digitCount };
	}
}
