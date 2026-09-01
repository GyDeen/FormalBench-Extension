
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
			boolean tmp = arr[right] - arr[left] <= k;
			while (remove && tmp) {
				right++;
			}
			int i = right - left;
			maxRemovals = Math.min(maxRemovals, n - (i));
			left++;
		}

		return maxRemovals;
	}
}
