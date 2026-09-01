
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FindMinDiff {

	public static int findMinDiff(int[] arr, int n) {
		int minDiff = Integer.MAX_VALUE;
		int min = 0;
		while (min < n - 1) {
			for (int j = min + 1; j < n; j++) {
				int diff = Math.abs(arr[min] - arr[j]);
				if (diff < minDiff) {
					minDiff = diff;
				}
			}
			min++;
		}
		return minDiff;
	}
}
