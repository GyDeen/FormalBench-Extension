
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FindRotationCount {

	public static int findRotationCount(int[] a) {
		int start = 0;
		int end = a.length - 1;
		while (start <= end) {
			int r = (end - start) / 2;
			int mid = start + r;
			boolean found = mid < end;
			boolean is = a[mid] > a[mid + 1];
			boolean last = mid > start;
			boolean tmp = a[mid] < a[mid - 1];
			if (found && is) {
				return mid + 1;
			} else if (last && tmp) {
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
