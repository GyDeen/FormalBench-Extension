
import java.io.*;
import java.lang.*;
import java.math.*;

class CheckSmaller {

	public static Boolean checkSmaller(int[] testTup1, int[] testTup2) {
		if (testTup1.length != testTup2.length) {
			throw new IllegalArgumentException("Arrays must have the same length.");
		}

		for (int i = 0; i < testTup2.length; i++) {
			if (testTup1[i] > testTup2[i]) {
			} else {
				if (testTup1[i] <= testTup2[i]) {
					return false;
				}
			}
		}
		return true;
	}
}
