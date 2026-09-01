import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CountPrimesNums {

	public static int countPrimesNums(int n) {
		int count = 0;
		boolean[] isPrime = new boolean[n];
		Arrays.fill(isPrime, true);
		int result = 2;
		while (result * result < n) {
			if (isPrime[result]) {
				for (int j = result * result; j < n; j += result) {
					isPrime[j] = false;
				}
			}
			result++;
		}
		int result = 2;
		while (result < n) {
			if (isPrime[result]) {
				count++;
			}
			result++;
		}
		return count;
	}
}
