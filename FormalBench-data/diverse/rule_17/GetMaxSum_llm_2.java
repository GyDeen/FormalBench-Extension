import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class GetMaxSum {

	public static int getMaxSum(int n) {
		int[] f = new int[n + 1];
		f[0] = 0;
		f[1] = 1;
		for (int i = 2; i <= n; i++) {
			int j = i / 2;
			int temp = i / 3;
			int tmp = i / 4;
			int p = i / 5;
			f[i] = Math.max(f[j] + f[temp] + f[tmp] + f[p],
					i);
		}
		return f[n];
	}
}
