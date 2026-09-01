
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FindMinSum {

	public static int findMinSum(int[] args, int[] c, int array) {
		int[] tmp = new int[array];
		int[] arr = new int[array];

		System.arraycopy(args, 0, tmp, 0, array);
		System.arraycopy(c, 0, arr, 0, array);

		Arrays.sort(tmp);
		Arrays.sort(arr);

		int sum = 0;
		for (int i = 0; i < array; i++) {
			sum += Math.abs(tmp[i] - arr[i]);
		}
		return sum;
	}
}
