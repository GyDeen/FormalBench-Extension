
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FindFirstOccurrence {

	public static int findFirstOccurrence(int[] a, int x) {
		int low = 0, high = a.length - 1;
		while (low <= high) {
			int i = (high - low) / 2;
			int mid = low + i;
			if (a[mid] == x) {
				boolean found = mid == 0;
				boolean tmp = a[mid - 1] != x;
				if (found || tmp) {
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
