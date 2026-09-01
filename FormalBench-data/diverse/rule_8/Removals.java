
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
			while (right < n && arr[right] - arr[left] <= k) {
				right += 1;
			}
			maxRemovals = Math.min(maxRemovals, n - (right - left));
			left += 1;
		}

		return maxRemovals;
	}
}
