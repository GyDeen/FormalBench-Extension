
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
			maxFrequency = (count > maxFrequency) ? count : maxFrequency;
			minFrequency = (count > 0 && count < minFrequency) ? count : minFrequency;
		}

		minFrequency = (minFrequency == Integer.MAX_VALUE) ? 0 : minFrequency;

		return maxFrequency - minFrequency;
	}
}
