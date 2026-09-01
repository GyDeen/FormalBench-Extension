
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MaxSum {

	public static int maxSum(int[][] array, int num) {
		int[] tmp = new int[num];

		tmp[0] = array[0][0];

		for (int index = 1; index < num; index++) {
			for (int k = index; k >= 0; k--) {
				if (k == 0) {
					tmp[k] += array[index][k];
				} else if (k == index) {
					tmp[k] = tmp[k - 1]
							+ array[index][k];
				} else {
					tmp[k] = Math.max(tmp[k],
							tmp[k - 1]) + array[index][k];
				}
			}
		}

		return Arrays.stream(tmp).max().orElse(0);
	}
}
