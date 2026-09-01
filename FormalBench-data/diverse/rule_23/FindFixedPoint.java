
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FindFixedPoint {

	public static int findFixedPoint(int[] arr, int n) {
		int start = 0;
		int end = n - 1;
		while (end >= start) {
			int mid = start + (end - start) / 2;
			if (arr[mid] == mid) {
				return mid;
			} else if (mid > arr[mid]) {
				start = mid + 1;
			} else {
				end = mid - 1;
			}
		}
		return -1;
	}
}
