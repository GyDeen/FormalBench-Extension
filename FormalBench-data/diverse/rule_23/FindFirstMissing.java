
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FindFirstMissing {

	public static int findFirstMissing(int[] array, int start, int end) {
		int min = start;
		int max = end;
		int mid;

		while (max >= min) {
			mid = (min + max) / 2;
			int current = array[mid];

			if (current == mid) {
				min = mid + 1;
			} else if (mid > current) {
				min = mid + 1;
			} else {
				max = mid - 1;
			}
		}
		return min;
	}
}
