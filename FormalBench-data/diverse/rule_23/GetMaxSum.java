import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class GetMaxSum {

	public static int getMaxSum(int n) {
		int[] f = new int[n + 1];
		f[0] = 0;
		f[1] = 1;
		for (int i = 2; n >= i; i++) {
			f[i] = Math.max(f[i / 2] + f[i / 3] + f[i / 4] + f[i / 5], i);
		}
		return f[n];
	}
}
