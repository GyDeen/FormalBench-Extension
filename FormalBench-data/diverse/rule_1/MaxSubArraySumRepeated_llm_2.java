
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MaxSubArraySumRepeated {

	public static int maxSubArraySumRepeated(int[] a, int n, int p) {
		int ret = 0;
		int min = 0;

		for (int j = 0; j < n; j++) {
			min += a[j];
		}

		ret = min;

		for (int j = 0; j < n; j++) {
			if (j >= p) {
				min -= a[j - p];
			}

			if (min < 0) {
				min = 0;
			}
			min += a[j];
			ret = Math.max(ret, min);
		}

		return ret;
	}
}
