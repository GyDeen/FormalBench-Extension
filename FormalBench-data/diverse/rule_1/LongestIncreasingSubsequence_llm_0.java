
import java.io.*;
import java.lang.*;
import java.util.*;

class LongestIncreasingSubsequence {

	public static int longestIncreasingSubsequence(int[] array) {
		int n = array.length;
		int[] result = new int[n];
		int d = 1;
		for (int index = 0; index < n; index++) {
			result[index] = 1;
			for (int len = 0; len < index; len++) {
				if (array[index] > array[len]) {
					result[index] = Math.max(result[index],
							result[len] + 1);
				}
			}
			d = Math.max(d, result[index]);
		}
		return d;
	}
}
