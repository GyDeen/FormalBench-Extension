import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class GetMaxSum {

	public static int getMaxSum(int n) {
		int[] f = new int[n + 1];
		f[0] = 0;
		f[1] = 1;
		int num = 2;
		while (num <= n) {
			f[num] = Math.max(f[num / 2] + f[num / 3]
					+ f[num / 4] + f[num / 5], num);
			num++;
		}
		return f[n];
	}
}
