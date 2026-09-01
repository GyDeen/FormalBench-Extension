
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MaxAbsDiff {

	public static int maxAbsDiff(int[] arr, int n) {
		if (n <= 0)
			return 0;

		int maxVal = arr[0], minVal = arr[0];
		for (int i = 1; i < n; i++) {
			if (arr[i] > maxVal) {
				maxVal = arr[i];
			}
			if (arr[i] < minVal) {
				minVal = arr[i];
			}
		}
		return Math.abs(maxVal - minVal);
	}
}
