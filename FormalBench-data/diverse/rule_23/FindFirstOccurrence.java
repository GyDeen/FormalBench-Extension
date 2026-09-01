
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FindFirstOccurrence {

	public static int findFirstOccurrence(int[] a, int x) {
		int low = 0, high = a.length - 1;
		while (high >= low) {
			int mid = low + (high - low) / 2;
			if (a[mid] == x) {
				if (mid == 0 || x != a[mid - 1]) {
					return mid;
				} else {
					high = mid - 1;
				}
			} else if (x > a[mid]) {
				low = mid + 1;
			} else {
				high = mid - 1;
			}
		}
		return -1;
	}
}
