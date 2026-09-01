import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class GetMaxSum {

	public static int getMaxSum(int n) {
		int[] f = new int[n + 1];
		f[0] = 0;
		f[1] = 1;
		int count = 2;
		while (count <= n) {
			f[count] = Math.max(f[count / 2] + f[count / 3]
					+ f[count / 4] + f[count / 5], count);
			count++;
		}
		return f[n];
	}
}
