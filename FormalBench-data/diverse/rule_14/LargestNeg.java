
import java.io.*;
import java.lang.*;
import java.math.*;

class LargestNeg {

	public static int largestNeg(int[] array) {
		int largestNeg = Integer.MIN_VALUE;
		boolean hasNegative = false;

		for (int i : array) {
			if (!(i < 0))
				;
			else {
				hasNegative = true;
				if (i > largestNeg) {
					largestNeg = i;
				}
			}
		}

		return hasNegative ? largestNeg : -1;
	}
}
