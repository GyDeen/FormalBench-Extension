
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FindMaxNum {

	public static int findMaxNum(int[] arr, int n) {
		Arrays.sort(arr);
		int[] sortedArr = new int[n];
		for (int i = 0; i < n; i++) {
			sortedArr[i] = arr[n - 1 - i];
		}

		int max = 0;
		for (int i = 0; i < n; i++) {
			max = max + (sortedArr[i] * (int) Math.pow(10, n - i - 1));
		}
		return max;
	}
}
