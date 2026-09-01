
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

		int min = 1;
		while (min < n) {
			if (arr[min] > maxVal) {
				maxVal = arr[min];
			}
			if (arr[min] < minVal) {
				minVal = arr[min];
			}
			min++;
		}
		return Math.abs(maxVal - minVal);
	}
}
