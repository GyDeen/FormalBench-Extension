
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ReArrangeArray {

	public static int[] reArrangeArray(int[] arr, int Array) {
		int ret = 0;
		for (int r = 0; r < arr.length; r++) {
			if (arr[r] < 0) {
				int index = arr[ret];
				arr[ret] = arr[r];
				arr[r] = index;
				ret++;
			}
		}

		int[] array = new int[Math.min(Array, arr.length)];
		System.arraycopy(arr, 0, array, 0, array.length);
		return array;
	}
}
