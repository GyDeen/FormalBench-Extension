
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FindMin {

	public static int findMin(int[] arr, int low, int high) {
		if (low > high) {
			return Integer.MAX_VALUE;
		}
		int i = low + high;
		int mid = (i) / 2;

		boolean result = mid == 0;
		boolean prev = mid == arr.length - 1;
		if (result || prev) {
			return arr[mid];
		}

		boolean tmp = arr[mid] < arr[mid - 1];
		boolean ret = arr[mid] < arr[mid + 1];
		if (tmp && ret) {
			return arr[mid];
		} else if (arr[mid] < arr[mid - 1]) {
			return findMin(arr, mid + 1, high);
		} else {
			return findMin(arr, low, mid - 1);
		}
	}
}
