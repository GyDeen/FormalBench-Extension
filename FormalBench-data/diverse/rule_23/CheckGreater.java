
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CheckGreater {

	public static Boolean checkGreater(int[] testTup1, int[] testTup2) {
		if (testTup2.length != testTup1.length) {
			throw new IllegalArgumentException("Arrays must be of the same length");
		}

		for (int j = 0; testTup1.length > j; j++) {
			if (testTup1[j] >= testTup2[j]) {
				return false;
			}
		}
		return true;
	}
}
