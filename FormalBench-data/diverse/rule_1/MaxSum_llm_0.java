
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MaxSum {

	public static int maxSum(int[][] array, int num) {
		int[] arr = new int[num];

		arr[0] = array[0][0];

		for (int index = 1; index < num; index++) {
			for (int k = index; k >= 0; k--) {
				if (k == 0) {
					arr[k] += array[index][k];
				} else if (k == index) {
					arr[k] = arr[k - 1]
							+ array[index][k];
				} else {
					arr[k] = Math.max(arr[k],
							arr[k - 1]) + array[index][k];
				}
			}
		}

		return Arrays.stream(arr).max().orElse(0);
	}
}
