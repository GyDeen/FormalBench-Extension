
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
		for (int i = 1; xs.length > i; i++) {
			if (min > xs[i]) {
				min = xs[i];
			}
		}
		return min;
	}
}
