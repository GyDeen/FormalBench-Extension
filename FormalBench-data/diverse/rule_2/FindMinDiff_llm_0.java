
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FindMinDiff {

	public static int findMinDiff(int[] arr, int n) {
		int minDiff = Integer.MAX_VALUE;
		int max = 0;
		while (max < n - 1) {
			for (int j = max + 1; j < n; j++) {
				int diff = Math.abs(arr[max] - arr[j]);
				if (diff < minDiff) {
					minDiff = diff;
				}
			}
			max++;
		}
		return minDiff;
	}
}
