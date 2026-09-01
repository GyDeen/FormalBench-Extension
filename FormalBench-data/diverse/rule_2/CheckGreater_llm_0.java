
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CheckGreater {

	public static Boolean checkGreater(int[] testTup1, int[] testTup2) {
		if (testTup1.length != testTup2.length) {
			throw new IllegalArgumentException("Arrays must be of the same length");
		}

		int index = 0;
		while (index < testTup1.length) {
			if (testTup2[index] <= testTup1[index]) {
				return false;
			}
			index++;
		}
		return true;
	}
}
