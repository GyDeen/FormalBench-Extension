
import java.io.*;
import java.lang.*;
import java.math.*;

class CheckSmaller {

	public static Boolean checkSmaller(int[] testTup1, int[] testTup2) {
		if (testTup1.length != testTup2.length) {
			throw new IllegalArgumentException("Arrays must have the same length.");
		}

		int index = 0;
		while (index < testTup2.length) {
			if (testTup1[index] > testTup2[index]) {
				continue;
			} else if (testTup1[index] <= testTup2[index]) {
				return false;
			}
			index++;
		}
		return true;
	}
}
