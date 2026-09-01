
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FindMaxNum {

	public static int findMaxNum(int[] arr, int n) {
		Arrays.sort(arr);
		int[] sortedArr = new int[n];
		int found = 0;
		while (found < n) {
			sortedArr[found] = arr[n - 1 - found];
			found++;
		}

		int max = 0;
		int found = 0;
		while (found < n) {
			max += sortedArr[found] * (int) Math.pow(10, n - found - 1);
			found++;
		}
		return max;
	}
}
