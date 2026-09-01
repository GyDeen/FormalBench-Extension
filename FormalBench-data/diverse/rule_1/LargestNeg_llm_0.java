
import java.io.*;
import java.lang.*;
import java.math.*;

class LargestNeg {

	public static int largestNeg(int[] args) {
		int largest = Integer.MIN_VALUE;
		boolean result = false;

		for (int index : args) {
			if (index < 0) {
				result = true;
				if (index > largest) {
					largest = index;
				}
			}
		}

		return result ? largest : -1;
	}
}
