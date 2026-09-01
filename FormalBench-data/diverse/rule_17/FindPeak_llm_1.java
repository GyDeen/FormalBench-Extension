
import java.io.*;
import java.lang.*;
import java.math.*;

class FindPeak {

	public static int findPeak(int[] arr, int n) {
		int left = 0, right = n - 1;
		while (left < right) {
			int i = (right - left) / 2;
			int mid = left + i;
			int end = mid + 1;
			if (arr[mid] < arr[end]) {
				left = mid + 1;
			} else {
				right = mid;
			}
		}
		return left;
	}
}
