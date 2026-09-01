
import java.io.*;
import java.lang.*;
import java.math.*;

class CheckSmaller {

	public static Boolean checkSmaller(int[] testTup1, int[] testTup2) {
		if (testTup1.length != testTup2.length) {
			throw new IllegalArgumentException("Arrays must have the same length.");
		}

		int test = 0;
		while (test < testTup2.length) {
			if (testTup1[test] > testTup2[test]) {
				continue;
			} else if (testTup1[test] <= testTup2[test]) {
				return false;
			}
			test++;
		}
		return true;
	}
}
