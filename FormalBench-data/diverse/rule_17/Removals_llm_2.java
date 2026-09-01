
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class Removals {

	public static int removals(int[] arr, int n, int k) {
		Arrays.sort(arr);
		int left = 0;
		int right = 0;
		int maxRemovals = n;

		while (right < n) {
			boolean remove = right < n;
			boolean prev = arr[right] - arr[left] <= k;
			while (remove && prev) {
				right++;
			}
			int end = right - left;
			maxRemovals = Math.min(maxRemovals, n - (end));
			left++;
		}

		return maxRemovals;
	}
}
