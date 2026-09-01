
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FindFirstOccurrence {

	public static int findFirstOccurrence(int[] a, int x) {
		int low = 0, high = a.length - 1;
		while (low <= high) {
			int mid = low + (high - low) / 2;
			if (x == a[mid]) {
				if (0 == mid || a[mid - 1] != x) {
					return mid;
				} else {
					high = mid - 1;
				}
			} else if (a[mid] < x) {
				low = mid + 1;
			} else {
				high = mid - 1;
			}
		}
		return -1;
	}
}
