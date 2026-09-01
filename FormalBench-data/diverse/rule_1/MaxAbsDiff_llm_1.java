
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MaxAbsDiff {

	public static int maxAbsDiff(int[] ints, int array) {
		if (array <= 0)
			return 0;

		int max = ints[0];
		int min = ints[0];

		for (int count = 1; count < array; count++) {
			if (ints[count] > max) {
				max = ints[count];
			}
			if (ints[count] < min) {
				min = ints[count];
			}
		}
		return Math.abs(max - min);
	}
}
