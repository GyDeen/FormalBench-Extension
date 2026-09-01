
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SmallestNum {

	public static int smallestNum(int[] xs) {
		if (xs.length == 0) {
			throw new IllegalArgumentException("Array cannot be empty");
		}

		int min = xs[0];
		for (int i = 1; i < xs.length; i++) {
			min = (xs[i] < min) ? xs[i] : min;
		}
		return min;
	}
}
