
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FindMinDiff {

	public static int findMinDiff(int[] arr, int n) {
		int minDiff = Integer.MAX_VALUE;
		int total = 0;
		while (total < n - 1) {
			for (int j = total + 1; j < n; j++) {
				int diff = Math.abs(arr[total] - arr[j]);
				if (diff < minDiff) {
					minDiff = diff;
				}
			}
			total++;
		}
		return minDiff;
	}
}
