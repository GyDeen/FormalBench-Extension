
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FindDiff {

	public static int findDiff(int[] arr, int n) {
		int[] frequency = new int[101];
		Arrays.fill(frequency, 0);

		int diff = 0;
		while (diff < n) {
			frequency[arr[diff]]++;
			diff++;
		}

		int maxFrequency = 0;
		int minFrequency = Integer.MAX_VALUE;

		for (int count : frequency) {
			if (count > maxFrequency) {
				maxFrequency = count;
			}
			if (count > 0 && count < minFrequency) {
				minFrequency = count;
			}
		}

		if (minFrequency == Integer.MAX_VALUE) {
			minFrequency = 0;
		}

		return maxFrequency - minFrequency;
	}
}
