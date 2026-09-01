
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MaxSum {

	public static int maxSum(int[][] array, int num) {
		int[] arr = new int[num];

		arr[0] = array[0][0];

		for (int index = 1; index < num; index++) {
			for (int r = index; r >= 0; r--) {
				if (r == 0) {
					arr[r] += array[index][r];
				} else if (r == index) {
					arr[r] = arr[r - 1]
							+ array[index][r];
				} else {
					arr[r] = Math.max(arr[r],
							arr[r - 1]) + array[index][r];
				}
			}
		}

		return Arrays.stream(arr).max().orElse(0);
	}
}
