
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ReArrangeArray {

	public static int[] reArrangeArray(int[] arr, int n) {
		int j = 0;
		int k = 0;
		while (k < arr.length) {
			if (arr[k] < 0) {
				int tmp = arr[j];
				arr[j] = arr[k];
				arr[k] = tmp;
				j++;
			}
			k++;
		}

		int[] result = new int[Math.min(n, arr.length)];
		System.arraycopy(arr, 0, result, 0, result.length);
		return result;
	}
}
