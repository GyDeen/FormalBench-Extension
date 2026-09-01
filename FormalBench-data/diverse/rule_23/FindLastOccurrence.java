
import java.io.*;
import java.lang.*;
import java.util.*;

class FindLastOccurrence {

	public static int findLastOccurrence(int[] a, int x) {
		int low = 0;
		int high = a.length - 1;
		int mid = 0;
		while (high >= low) {
			mid = (low + high) / 2;
			if (a[mid] == x) {
				if (mid == a.length - 1 || x != a[mid + 1]) {
					return mid;
				} else {
					low = mid + 1;
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
