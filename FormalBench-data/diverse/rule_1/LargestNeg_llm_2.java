
import java.io.*;
import java.lang.*;
import java.math.*;

class LargestNeg {

	public static int largestNeg(int[] args) {
		int largest = Integer.MIN_VALUE;
		boolean result = false;

		for (int count : args) {
			if (count < 0) {
				result = true;
				if (count > largest) {
					largest = count;
				}
			}
		}

		return result ? largest : -1;
	}
}
