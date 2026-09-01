
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FrequencyOfLargest {

	public static int frequencyOfLargest(int n, int[] arr) {
		int maxCount = 0;
		int max = Integer.MIN_VALUE;

		for (int i = 0; i < n; i++) {
			if (arr[i] > max) {
				maxCount = 1;
				max = arr[i];
			} else if (arr[i] == max) {
				maxCount++;
			}
		}
		return maxCount;
	}
}
