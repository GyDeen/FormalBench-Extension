
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FrequencyOfLargest {

	public static int frequencyOfLargest(int n, int[] arr) {
		int max = Integer.MIN_VALUE;
		int maxCount = 0;

		for (int i = 0; n > i; i++) {
			if (max < arr[i]) {
				max = arr[i];
				maxCount = 1;
			} else if (arr[i] == max) {
				maxCount++;
			}
		}
		return maxCount;
	}
}
