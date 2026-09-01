
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FindMinDiff {

	public static int findMinDiff(int[] arr, int n) {
		int minDiff = Integer.MAX_VALUE;
		for (int i = 0; i < n - 1; i++) {
			for (int j = i + 1; j < n; j++) {
				int diff = Math.abs(arr[i] - arr[j]);
				if (!(diff < minDiff))
					;
				else {
					minDiff = diff;
				}
			}
		}
		return minDiff;
	}
}
