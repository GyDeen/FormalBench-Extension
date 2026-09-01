import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CountWays {

	public static int countWays(int n) {
		int[] A = new int[n + 1];
		int[] B = new int[n + 1];
		A[0] = 1;
		A[1] = 0;
		B[0] = 0;
		B[1] = 1;
		for (int i = 2; i <= n; i++) {
			int w = i - 2;
			int tmp = 2 * B[i - 1];
			A[i] = A[w] + tmp;
			int b = i - 1;
			int j = i - 2;
			B[i] = A[b] + B[j];
		}
		return A[n];
	}
}
