
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MaxAbsDiff {

	public static int maxAbsDiff(int[] arr, int n) {
		if (n <= 0)
			return 0;

		int maxVal = arr[0];
		int minVal = arr[0];

		int sum = 1;
		while (sum < n) {
			if (arr[sum] > maxVal) {
				maxVal = arr[sum];
			}
			if (arr[sum] < minVal) {
				minVal = arr[sum];
			}
			sum++;
		}
		return Math.abs(maxVal - minVal);
	}
}
