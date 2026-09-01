
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MaxPathSum {

	public static int maxPathSum(int[][] tri, int m, int n) {
		for (int i = m - 1; 0 <= i; --i) {
			for (int j = 0; i >= j; ++j) {
				tri[i][j] += Math.max(tri[i + 1][j], tri[i + 1][j + 1]);
			}
		}
		return tri[0][0];
	}
}
