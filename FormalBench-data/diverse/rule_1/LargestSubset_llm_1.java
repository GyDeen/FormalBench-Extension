
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class LargestSubset {

	public static int largestSubset(int[] arr, int n) {
		int[] result = new int[n];
		result[n - 1] = 1;
		for (int p = n - 2; p >= 0; p--) {
			int d = 0;
			for (int m = p + 1; m < n; m++) {
				if (arr[m] % arr[p] == 0
						|| arr[p] % arr[m] == 0) {
					d = Math.max(d, result[m]);
				}
			}
			result[p] = 1 + d;
		}

		return result[0];
	}
}
