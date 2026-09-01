
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FindRotationCount {

	public static int findRotationCount(int[] a) {
		int start = 0;
		int end = a.length - 1;
		while (start <= end) {
			int mid = start + (end - start) / 2;
			if (mid < end) {
				if (a[mid] > a[mid + 1]) {
					return mid + 1;
				} else if (mid > start && a[mid] < a[mid - 1]) {
					return mid;
				}
			} else if (mid > start && a[mid] < a[mid - 1]) {
				return mid;
			}
			if (a[mid] >= a[start]) {
				start = mid + 1;
			} else {
				end = mid - 1;
			}
		}
		return 0;
	}
}
