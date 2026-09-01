
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

		while (n > right) {
			while (n > right && k >= arr[right] - arr[left]) {
				right++;
			}
			maxRemovals = Math.min(maxRemovals, n - (right - left));
			left++;
		}

		return maxRemovals;
	}
}
