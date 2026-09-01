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
		int end = 2;
		while (end <= n) {
			A[end] = A[end - 2] + 2 * B[end - 1];
			B[end] = A[end - 1] + B[end - 2];
			end++;
		}
		return A[n];
	}
}
