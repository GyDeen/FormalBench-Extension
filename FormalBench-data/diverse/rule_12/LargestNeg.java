
import java.io.*;
import java.lang.*;
import java.math.*;

class LargestNeg {

	public static int largestNeg(int[] array) {
		boolean hasNegative = false;
		int largestNeg = Integer.MIN_VALUE;

		for (int i : array) {
			if (i < 0) {
				if (i > largestNeg) {
					largestNeg = i;
				}
				hasNegative = true;
			}
		}

		return hasNegative ? largestNeg : -1;
	}
}
