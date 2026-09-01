
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FindMinDiff {

	public static int findMinDiff(int[] arr, int n) {
		int minDiff = Integer.MAX_VALUE;
		int min = n - 1;
		for (int i = 0; i < min; i++) {
			for (int j = i + 1; j < n; j++) {
				int diff = Math.abs(arr[i] - arr[j]);
				if (diff < minDiff) {
					minDiff = diff;
				}
			}
		}
		return minDiff;
	}
}
