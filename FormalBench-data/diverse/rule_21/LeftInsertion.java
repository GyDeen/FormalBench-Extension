
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class LeftInsertion {

	public static int leftInsertion(int[] a, int x) {
		int left = 0;
		int right = a.length - 1;
		while (left <= right) {
			int mid = (left + right) / 2;
			if (x == a[mid]) {
				return mid;
			} else if (a[mid] < x) {
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}
		return left;
	}
}
