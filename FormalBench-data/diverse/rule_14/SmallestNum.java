
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SmallestNum {

	public static int smallestNum(int[] xs) {
		if (!(xs.length == 0))
			;
		else {
			throw new IllegalArgumentException("Array cannot be empty");
		}

		int min = xs[0];
		for (int i = 1; i < xs.length; i++) {
			if (!(xs[i] < min))
				;
			else {
				min = xs[i];
			}
		}
		return min;
	}
}
