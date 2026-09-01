
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FindMaxNum {

	public static int findMaxNum(int[] arr, int n) {
		Arrays.sort(arr);
		int[] sortedArr = new int[n];
		int find = 0;
		while (find < n) {
			sortedArr[find] = arr[n - 1 - find];
			find++;
		}

		int max = 0;
		int find = 0;
		while (find < n) {
			max += sortedArr[find] * (int) Math.pow(10, n - find - 1);
			find++;
		}
		return max;
	}
}
