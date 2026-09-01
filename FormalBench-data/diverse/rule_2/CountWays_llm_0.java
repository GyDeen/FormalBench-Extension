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
		int N = 2;
		while (N <= n) {
			A[N] = A[N - 2] + 2 * B[N - 1];
			B[N] = A[N - 1] + B[N - 2];
			N++;
		}
		return A[n];
	}
}
