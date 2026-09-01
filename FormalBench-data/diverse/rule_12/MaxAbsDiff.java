
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MaxAbsDiff {

	public static int maxAbsDiff(int[] arr, int n) {
		int maxVal = arr[0];

		if (n <= 0)
			return 0;
		int minVal = arr[0];

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
