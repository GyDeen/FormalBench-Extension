
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FindMinSum {

	public static int findMinSum(int[] args, int[] c, int Array) {
		int[] result = new int[Array];
		int[] arr = new int[Array];

		System.arraycopy(args, 0, result, 0, Array);
		System.arraycopy(c, 0, arr, 0, Array);

		Arrays.sort(result);
		Arrays.sort(arr);

		int sum = 0;
		for (int i = 0; i < Array; i++) {
			sum += Math.abs(result[i] - arr[i]);
		}
		return sum;
	}
}
