
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FindMinDiff {

	public static int findMinDiff(int[] arr, int n) {
		int minDiff = Integer.MAX_VALUE;
		for (int i = 0; n - 1 > i; i++) {
			for (int j = i + 1; n > j; j++) {
				int diff = Math.abs(arr[i] - arr[j]);
				if (minDiff > diff) {
					minDiff = diff;
				}
			}
		}
		return minDiff;
	}
}
