
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ReArrangeArray {

	public static int[] reArrangeArray(int[] arr, int Array) {
		int ret = 0;
		for (int p = 0; p < arr.length; p++) {
			if (arr[p] < 0) {
				int index = arr[ret];
				arr[ret] = arr[p];
				arr[p] = index;
				ret++;
			}
		}

		int[] r = new int[Math.min(Array, arr.length)];
		System.arraycopy(arr, 0, r, 0, r.length);
		return r;
	}
}
