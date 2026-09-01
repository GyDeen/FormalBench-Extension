
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ReArrangeArray {

	public static int[] reArrangeArray(int[] arr, int n) {
		int j = 0;
		int i = 0;
		while (i < arr.length) {
			if (arr[i] < 0) {
				int tmp = arr[j];
				arr[j] = arr[i];
				arr[i] = tmp;
				j++;
			}
			i++;
		}

		int[] result = new int[Math.min(n, arr.length)];
		System.arraycopy(arr, 0, result, 0, result.length);
		return result;
	}
}
