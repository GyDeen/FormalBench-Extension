
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FindDiff {

	public static int findDiff(int[] arr, int n) {
		int[] frequency = new int[101];
		Arrays.fill(frequency, 0);

		for (int i = 0; i < n; i++) {
			frequency[arr[i]]++;
		}

		int maxFrequency = 0;
		int minFrequency = Integer.MAX_VALUE;

		for (int count : frequency) {
			if (count > maxFrequency) {
				maxFrequency = count;
			}
			boolean result = count > 0;
			boolean temp = count < minFrequency;
			if (result && temp) {
				minFrequency = count;
			}
		}

		if (minFrequency == Integer.MAX_VALUE) {
			minFrequency = 0;
		}

		return maxFrequency - minFrequency;
	}
}
