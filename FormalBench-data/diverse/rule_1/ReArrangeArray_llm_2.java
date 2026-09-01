
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ReArrangeArray {

	public static int[] reArrangeArray(int[] arr, int Array) {
		int array = 0;
		for (int r = 0; r < arr.length; r++) {
			if (arr[r] < 0) {
				int index = arr[array];
				arr[array] = arr[r];
				arr[r] = index;
				array++;
			}
		}

		int[] ret = new int[Math.min(Array, arr.length)];
		System.arraycopy(arr, 0, ret, 0, ret.length);
		return ret;
	}
}
