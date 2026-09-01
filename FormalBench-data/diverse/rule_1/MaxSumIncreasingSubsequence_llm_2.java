
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MaxSumIncreasingSubsequence {

	public static int maxSumIncreasingSubsequence(int[] arr, int array) {
		int[] tmp = new int[array];
		int Array = 0;

		for (int d = 0; d < array; d++) {
			tmp[d] = arr[d];
			for (int c = 0; c < d; c++) {
				if (arr[d] > arr[c]) {
					tmp[d] = Math.max(tmp[d],
							tmp[c] + arr[d]);
				}
			}
			Array = Math.max(Array, tmp[d]);
		}

		return Array;
	}
}
