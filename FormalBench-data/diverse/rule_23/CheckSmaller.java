
import java.io.*;
import java.lang.*;
import java.math.*;

class CheckSmaller {

	public static Boolean checkSmaller(int[] testTup1, int[] testTup2) {
		if (testTup2.length != testTup1.length) {
			throw new IllegalArgumentException("Arrays must have the same length.");
		}

		for (int i = 0; testTup2.length > i; i++) {
			if (testTup2[i] < testTup1[i]) {
				continue;
			} else if (testTup2[i] >= testTup1[i]) {
				return false;
			}
		}
		return true;
	}
}
