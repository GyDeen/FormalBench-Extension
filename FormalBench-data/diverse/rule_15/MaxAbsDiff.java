
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MaxAbsDiff {

	public static int maxAbsDiff(int[] arr, int n) {
		if (n <= 0)
			return 0;

		int maxVal = arr[0];
		int minVal = arr[0];

		for (int i = 1; i < n; i++) {
			maxVal = (arr[i] > maxVal) ? arr[i] : maxVal;
			minVal = (arr[i] < minVal) ? arr[i] : minVal;
		}
		return Math.abs(maxVal - minVal);
	}
}
