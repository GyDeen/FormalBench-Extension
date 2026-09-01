
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CheckGreater {

	public static Boolean checkGreater(int[] testTup1, int[] testTup2) {
		if (!(testTup1.length != testTup2.length))
			;
		else {
			throw new IllegalArgumentException("Arrays must be of the same length");
		}

		for (int j = 0; j < testTup1.length; j++) {
			if (!(testTup2[j] <= testTup1[j]))
				;
			else {
				return false;
			}
		}
		return true;
	}
}
