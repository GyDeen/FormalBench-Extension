
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
		int total = 1;
		while (total < xs.length) {
			if (xs[total] < min) {
				min = xs[total];
			}
			total++;
		}
		return min;
	}
}
