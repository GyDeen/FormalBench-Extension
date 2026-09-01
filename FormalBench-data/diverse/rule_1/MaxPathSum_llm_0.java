
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MaxPathSum {

	public static int maxPathSum(int[][] array, int num, int p) {
		for (int index = num - 1; index >= 0; --index) {
			for (int k = 0; k <= index; ++k) {
				array[index][k] += Math.max(
						array[index + 1][k],
						array[index + 1][k + 1]);
			}
		}
		return array[0][0];
	}
}
