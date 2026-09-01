
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class Last {

	public static int last(int[] arr, int x, int n) {
		int i = 0, j = n - 1;
		int result = -1;

		while (i <= j) {
			int mid = (i + j) / 2;
			if (arr[mid] == x) {
				result = mid;
				i = mid + 1;
			} else if (arr[mid] < x) {
				i = mid + 1;
			} else {
				j = mid - 1;
			}
		}

		return result;
	}
}
