
import java.io.*;
import java.lang.*;
import java.util.*;

class FindLastOccurrence {

	public static int findLastOccurrence(int[] a, int x) {
		int low = 0;
		int high = a.length - 1;
		int mid = 0;
		while (low <= high) {
			mid = (low + high) / 2;
			if (x == a[mid]) {
				if (a.length - 1 == mid || a[mid + 1] != x) {
					return mid;
				} else {
					low = mid + 1;
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
