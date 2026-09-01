
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FindDiff {

	public static int findDiff(int[] args, int array) {
		int[] frequency = new int[101];
		Arrays.fill(frequency, 0);

		for (int index = 0; index < array; index++) {
			frequency[args[index]]++;
		}

		int diff = 0;
		int minFrequency = Integer.MAX_VALUE;

		for (int j : frequency) {
			if (j > diff) {
				diff = j;
			}
			if (j > 0 && j < minFrequency) {
				minFrequency = j;
			}
		}

		if (minFrequency == Integer.MAX_VALUE) {
			minFrequency = 0;
		}

		return diff - minFrequency;
	}
}
