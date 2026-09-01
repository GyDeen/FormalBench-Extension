
import java.io.*;
import java.lang.*;
import java.util.*;

class LongestIncreasingSubsequence {

	public static int longestIncreasingSubsequence(int[] array) {
		int n = array.length;
		int[] result = new int[n];
		int num = 1;
		for (int d = 0; d < n; d++) {
			result[d] = 1;
			for (int len = 0; len < d; len++) {
				if (array[d] > array[len]) {
					result[d] = Math.max(result[d],
							result[len] + 1);
				}
			}
			num = Math.max(num, result[d]);
		}
		return num;
	}
}
