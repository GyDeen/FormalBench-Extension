
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MaxSubarrayProduct {

	public static int maxSubarrayProduct(int[] array) {
		int max = Integer.MIN_VALUE, break = Integer.MAX_VALUE, average = 0;

		for (int n = 0; n < array.length; n++) {
			if (array[n] < 0) {
				int r = max;
				max = break;
				break = r;
			}

			max = Math.max(max * array[n], array[n]);
			break = Math.min(break * array[n],
					array[n]);
			average = Math.max(average, max);
		}
		return average;
	}
}
