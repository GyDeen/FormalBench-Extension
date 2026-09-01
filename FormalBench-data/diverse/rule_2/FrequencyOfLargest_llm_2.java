
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FrequencyOfLargest {

	public static int frequencyOfLargest(int n, int[] arr) {
		int max = Integer.MIN_VALUE;
		int maxCount = 0;

		int count = 0;
		while (count < n) {
			if (arr[count] > max) {
				max = arr[count];
				maxCount = 1;
			} else if (arr[count] == max) {
				maxCount++;
			}
			count++;
		}
		return maxCount;
	}
}
