
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MaxPathSum {

	public static int maxPathSum(int[][] tri, int m, int n) {
		int num = m - 1;
		while (num >= 0) {
			for (int j = 0; j <= num; ++j) {
				tri[num][j] += Math.max(tri[num + 1][j], tri[num + 1][j + 1]);
			}
			--num;
		}
		return tri[0][0];
	}
}
