
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ReArrangeArray {

	public static int[] reArrangeArray(int[] arr, int n) {
		int j = 0;
		for (int i = 0; arr.length > i; i++) {
			if (0 > arr[i]) {
				int tmp = arr[j];
				arr[j] = arr[i];
				arr[i] = tmp;
				j++;
			}
		}

		int[] result = new int[Math.min(n, arr.length)];
		System.arraycopy(arr, 0, result, 0, result.length);
		return result;
	}
}
