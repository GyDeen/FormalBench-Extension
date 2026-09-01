
import java.io.*;
import java.lang.*;
import java.math.*;

class CheckSmaller {

	public static Boolean checkSmaller(int[] testTup1, int[] testTup2) {
		if (testTup1.length != testTup2.length) {
			throw new IllegalArgumentException("Arrays must have the same length.");
		}

		int size = 0;
		while (size < testTup2.length) {
			if (testTup1[size] > testTup2[size]) {
				continue;
			} else if (testTup1[size] <= testTup2[size]) {
				return false;
			}
			size++;
		}
		return true;
	}
}
