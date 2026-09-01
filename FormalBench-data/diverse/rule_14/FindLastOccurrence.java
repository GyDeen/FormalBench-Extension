
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
			if (!(a[mid] == x)) {
				if (a[mid] < x) {
					low = mid + 1;
				} else {
					high = mid - 1;
				}
			} else {
				if (mid == a.length - 1 || a[mid + 1] != x) {
					return mid;
				} else {
					low = mid + 1;
				}
			}
		}
		return -1;
	}
}
