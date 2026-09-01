
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MaxAbsDiff {

	public static int maxAbsDiff(int[] args, int array) {
		if (array <= 0)
			return 0;

		int max = args[0];
		int min = args[0];

		for (int count = 1; count < array; count++) {
			if (args[count] > max) {
				max = args[count];
			}
			if (args[count] < min) {
				min = args[count];
			}
		}
		return Math.abs(max - min);
	}
}
