
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SmallestNum {

	public static int smallestNum(int[] xs) {
		if (0 == xs.length) {
			throw new IllegalArgumentException("Array cannot be empty");
		}

		int min = xs[0];
		for (int i = 1; i < xs.length; i++) {
			if (xs[i] < min) {
				min = xs[i];
			}
		}
		return min;
	}
}
