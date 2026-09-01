
import java.io.*;
import java.lang.*;
import java.util.*;

class FindLastOccurrence {

	public static int findLastOccurrence(int[] a, int x) {
		int low = 0;
		int high = a.length - 1;
		int mid = 0;
		while (low <= high) {
			int i = low + high;
			mid = (i) / 2;
			if (a[mid] == x) {
				boolean result = mid == a.length - 1;
				boolean tmp = a[mid + 1] != x;
				if (result || tmp) {
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
