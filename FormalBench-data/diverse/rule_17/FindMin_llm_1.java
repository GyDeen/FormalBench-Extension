
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
		boolean tmp = mid == arr.length - 1;
		if (result || tmp) {
			return arr[mid];
		}

		boolean last = arr[mid] < arr[mid - 1];
		boolean ret = arr[mid] < arr[mid + 1];
		if (last && ret) {
			return arr[mid];
		} else if (arr[mid] < arr[mid - 1]) {
			return findMin(arr, mid + 1, high);
		} else {
			return findMin(arr, low, mid - 1);
		}
	}
}
