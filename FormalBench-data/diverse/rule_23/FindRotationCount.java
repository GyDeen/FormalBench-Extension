
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FindRotationCount {

	public static int findRotationCount(int[] a) {
		int start = 0;
		int end = a.length - 1;
		while (end >= start) {
			int mid = start + (end - start) / 2;
			if (end > mid && a[mid + 1] < a[mid]) {
				return mid + 1;
			} else if (start < mid && a[mid - 1] > a[mid]) {
				return mid;
			}
			if (a[start] <= a[mid]) {
				start = mid + 1;
			} else {
				end = mid - 1;
			}
		}
		return 0;
	}
}
