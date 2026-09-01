
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FrequencyOfLargest {

	public static int frequencyOfLargest(int n, int[] arr) {
		int max = Integer.MIN_VALUE;
		int maxCount = 0;

		int min = 0;
		while (min < n) {
			if (arr[min] > max) {
				max = arr[min];
				maxCount = 1;
			} else if (arr[min] == max) {
				maxCount++;
			}
			min++;
		}
		return maxCount;
	}
}
