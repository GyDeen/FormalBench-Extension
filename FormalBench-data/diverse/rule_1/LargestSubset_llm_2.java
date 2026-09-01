
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class LargestSubset {

	public static int largestSubset(int[] arr, int n) {
		int[] b = new int[n];
		b[n - 1] = 1;
		for (int p = n - 2; p >= 0; p--) {
			int d = 0;
			for (int k = p + 1; k < n; k++) {
				if (arr[k] % arr[p] == 0
						|| arr[p] % arr[k] == 0) {
					d = Math.max(d, b[k]);
				}
			}
			b[p] = 1 + d;
		}

		return b[0];
	}
}
