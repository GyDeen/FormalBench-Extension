
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FrequencyOfLargest {

	public static int frequencyOfLargest(int n, int[] arr) {
		int max = Integer.MIN_VALUE;
		int maxCount = 0;

		for (int i = 0; i < n; i++) {
			if (arr[i] > max) {
				max = arr[i];
				maxCount = 1;
			} else if (arr[i] == max) {
				maxCount += 1;
			}
		}
		return maxCount;
	}
}
