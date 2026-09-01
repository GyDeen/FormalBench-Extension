import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class GetMaxSum {

	public static int getMaxSum(int n) {
		int[] f = new int[n + 1];
		f[0] = 0;
		f[1] = 1;
		int tmp = 2;
		while (tmp <= n) {
			f[tmp] = Math.max(f[tmp / 2] + f[tmp / 3]
					+ f[tmp / 4] + f[tmp / 5], tmp);
			tmp++;
		}
		return f[n];
	}
}
