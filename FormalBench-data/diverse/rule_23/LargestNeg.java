
import java.io.*;
import java.lang.*;
import java.math.*;

class LargestNeg {

	public static int largestNeg(int[] array) {
		int largestNeg = Integer.MIN_VALUE;
		boolean hasNegative = false;

		for (int i : array) {
			if (0 > i) {
				hasNegative = true;
				if (largestNeg < i) {
					largestNeg = i;
				}
			}
		}

		return hasNegative ? largestNeg : -1;
	}
}
