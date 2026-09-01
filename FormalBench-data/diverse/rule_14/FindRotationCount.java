
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
			if (!(mid < end && a[mid] > a[mid + 1])) {
				if (mid > start && a[mid] < a[mid - 1]) {
					return mid;
				}
			} else {
				return mid + 1;
			}
			if (!(a[mid] >= a[start])) {
				end = mid - 1;
			} else {
				start = mid + 1;
			}
		}
		return 0;
	}
}
