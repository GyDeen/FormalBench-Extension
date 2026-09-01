
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FindMinSum {

	public static int findMinSum(int[] a, int[] b, int n) {
		int[] arr1 = new int[n];
		int[] arr2 = new int[n];

		System.arraycopy(a, 0, arr1, 0, n);
		System.arraycopy(b, 0, arr2, 0, n);

		Arrays.sort(arr1);
		Arrays.sort(arr2);

		int sum = 0;
		int max = 0;
		while (max < n) {
			sum += Math.abs(arr1[max] - arr2[max]);
			max++;
		}
		return sum;
	}
}
