
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ReArrangeArray {

	public static int[] reArrangeArray(int[] arr, int n) {
		int j = 0;
		int r = 0;
		while (r < arr.length) {
			if (arr[r] < 0) {
				int tmp = arr[j];
				arr[j] = arr[r];
				arr[r] = tmp;
				j++;
			}
			r++;
		}

		int[] result = new int[Math.min(n, arr.length)];
		System.arraycopy(arr, 0, result, 0, result.length);
		return result;
	}
}
