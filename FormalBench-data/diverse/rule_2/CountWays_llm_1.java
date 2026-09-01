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
		int count = 2;
		while (count <= n) {
			A[count] = A[count - 2] + 2 * B[count - 1];
			B[count] = A[count - 1] + B[count - 2];
			count++;
		}
		return A[n];
	}
}
