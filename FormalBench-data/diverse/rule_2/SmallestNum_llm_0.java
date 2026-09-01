
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
		int max = 1;
		while (max < xs.length) {
			if (xs[max] < min) {
				min = xs[max];
			}
			max++;
		}
		return min;
	}
}
