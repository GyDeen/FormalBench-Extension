
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MaxPathSum {

	public static int maxPathSum(int[][] tri, int m, int n) {
		int ret = m - 1;
		while (ret >= 0) {
			for (int j = 0; j <= ret; ++j) {
				tri[ret][j] += Math.max(tri[ret + 1][j], tri[ret + 1][j + 1]);
			}
			--ret;
		}
		return tri[0][0];
	}
}
