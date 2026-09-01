
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FindRotationCount {

	public static int findRotationCount(int[] arr) {
		int r = 0;
		int stop = arr.length - 1;
		while (r <= stop) {
			int i = r + (stop - r) / 2;
			if (i < stop
					&& arr[i] > arr[i + 1]) {
				return i + 1;
			} else if (i > r
					&& arr[i] < arr[i - 1]) {
				return i;
			}
			if (arr[i] >= arr[r]) {
				r = i + 1;
			} else {
				stop = i - 1;
			}
		}
		return 0;
	}
}
