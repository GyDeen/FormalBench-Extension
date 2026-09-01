
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FrequencyOfLargest {

	public static int frequencyOfLargest(int n, int[] arr) {
		int max = Integer.MIN_VALUE;
		int maxCount = 0;

		int current = 0;
		while (current < n) {
			if (arr[current] > max) {
				max = arr[current];
				maxCount = 1;
			} else if (arr[current] == max) {
				maxCount++;
			}
			current++;
		}
		return maxCount;
	}
}
