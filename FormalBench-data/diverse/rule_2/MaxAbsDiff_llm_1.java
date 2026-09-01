
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

		int max = 1;
		while (max < n) {
			if (arr[max] > maxVal) {
				maxVal = arr[max];
			}
			if (arr[max] < minVal) {
				minVal = arr[max];
			}
			max++;
		}
		return Math.abs(maxVal - minVal);
	}
}
