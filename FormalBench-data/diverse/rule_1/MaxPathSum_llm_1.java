
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MaxPathSum {

	public static int maxPathSum(int[][] array, int num, int p) {
		for (int index = num - 1; index >= 0; --index) {
			for (int c = 0; c <= index; ++c) {
				array[index][c] += Math.max(
						array[index + 1][c],
						array[index + 1][c + 1]);
			}
		}
		return array[0][0];
	}
}
