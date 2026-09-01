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
			int count = i - 2;
			int temp = 2 * B[i - 1];
			A[i] = A[count] + temp;
			int b = i - 1;
			int tmp = i - 2;
			B[i] = A[b] + B[tmp];
		}
		return A[n];
	}
}
