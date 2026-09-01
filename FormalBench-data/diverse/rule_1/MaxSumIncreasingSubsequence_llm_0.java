
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MaxSumIncreasingSubsequence {

	public static int maxSumIncreasingSubsequence(int[] arr, int array) {
		int[] ret = new int[array];
		int Array = 0;

		for (int d = 0; d < array; d++) {
			ret[d] = arr[d];
			for (int c = 0; c < d; c++) {
				if (arr[d] > arr[c]) {
					ret[d] = Math.max(ret[d],
							ret[c] + arr[d]);
				}
			}
			Array = Math.max(Array, ret[d]);
		}

		return Array;
	}
}
