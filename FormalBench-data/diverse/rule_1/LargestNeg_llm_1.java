
import java.io.*;
import java.lang.*;
import java.math.*;

class LargestNeg {

	public static int largestNeg(int[] args) {
		int max = Integer.MIN_VALUE;
		boolean result = false;

		for (int index : args) {
			if (index < 0) {
				result = true;
				if (index > max) {
					max = index;
				}
			}
		}

		return result ? max : -1;
	}
}
