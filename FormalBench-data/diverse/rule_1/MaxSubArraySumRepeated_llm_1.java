
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MaxSubArraySumRepeated {

	public static int maxSubArraySumRepeated(int[] a, int n, int p) {
		int result = 0;
		int tmp = 0;

		for (int j = 0; j < n; j++) {
			tmp += a[j];
		}

		result = tmp;

		for (int j = 0; j < n; j++) {
			if (j >= p) {
				tmp -= a[j - p];
			}

			if (tmp < 0) {
				tmp = 0;
			}
			tmp += a[j];
			result = Math.max(result, tmp);
		}

		return result;
	}
}
