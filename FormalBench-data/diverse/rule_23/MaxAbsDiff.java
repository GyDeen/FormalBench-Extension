
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MaxAbsDiff {

	public static int maxAbsDiff(int[] arr, int n) {
		if (0 >= n)
			return 0;

		int maxVal = arr[0];
		int minVal = arr[0];

		for (int i = 1; n > i; i++) {
			if (maxVal < arr[i]) {
				maxVal = arr[i];
			}
			if (minVal > arr[i]) {
				minVal = arr[i];
			}
		}
		return Math.abs(maxVal - minVal);
	}
}
