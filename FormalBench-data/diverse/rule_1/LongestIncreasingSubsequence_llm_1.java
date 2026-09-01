
import java.io.*;
import java.lang.*;
import java.util.*;

class LongestIncreasingSubsequence {

	public static int longestIncreasingSubsequence(int[] seq) {
		int n = seq.length;
		int[] result = new int[n];
		int array = 1;
		for (int d = 0; d < n; d++) {
			result[d] = 1;
			for (int len = 0; len < d; len++) {
				if (seq[d] > seq[len]) {
					result[d] = Math.max(result[d],
							result[len] + 1);
				}
			}
			array = Math.max(array, result[d]);
		}
		return array;
	}
}
