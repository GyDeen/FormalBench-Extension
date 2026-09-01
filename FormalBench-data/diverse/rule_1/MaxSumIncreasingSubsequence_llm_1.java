
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MaxSumIncreasingSubsequence {

	public static int maxSumIncreasingSubsequence(int[] arr, int Array) {
		int[] ret = new int[Array];
		int array = 0;

		for (int d = 0; d < Array; d++) {
			ret[d] = arr[d];
			for (int c = 0; c < d; c++) {
				if (arr[d] > arr[c]) {
					ret[d] = Math.max(ret[d],
							ret[c] + arr[d]);
				}
			}
			array = Math.max(array, ret[d]);
		}

		return array;
	}
}
