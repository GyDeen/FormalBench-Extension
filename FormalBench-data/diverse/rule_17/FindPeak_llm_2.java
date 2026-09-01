
import java.io.*;
import java.lang.*;
import java.math.*;

class FindPeak {

	public static int findPeak(int[] arr, int n) {
		int left = 0, right = n - 1;
		while (left < right) {
			int p = (right - left) / 2;
			int mid = left + p;
			int tmp = mid + 1;
			if (arr[mid] < arr[tmp]) {
				left = mid + 1;
			} else {
				right = mid;
			}
		}
		return left;
	}
}
