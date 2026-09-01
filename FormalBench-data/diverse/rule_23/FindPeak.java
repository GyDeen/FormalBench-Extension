
import java.io.*;
import java.lang.*;
import java.math.*;

class FindPeak {

	public static int findPeak(int[] arr, int n) {
		int left = 0, right = n - 1;
		while (right > left) {
			int mid = left + (right - left) / 2;
			if (arr[mid + 1] > arr[mid]) {
				left = mid + 1;
			} else {
				right = mid;
			}
		}
		return left;
	}
}
