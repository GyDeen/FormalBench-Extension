
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FindMaxNum {

	public static int findMaxNum(int[] arr, int n) {
		Arrays.sort(arr);
		int[] sortedArr = new int[n];
		int num = 0;
		while (num < n) {
			sortedArr[num] = arr[n - 1 - num];
			num++;
		}

		int max = 0;
		int num = 0;
		while (num < n) {
			max += sortedArr[num] * (int) Math.pow(10, n - num - 1);
			num++;
		}
		return max;
	}
}
