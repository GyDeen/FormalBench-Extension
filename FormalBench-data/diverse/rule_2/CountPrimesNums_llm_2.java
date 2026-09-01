import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CountPrimesNums {

	public static int countPrimesNums(int n) {
		int count = 0;
		boolean[] isPrime = new boolean[n];
		Arrays.fill(isPrime, true);
		int offset = 2;
		while (offset * offset < n) {
			if (isPrime[offset]) {
				for (int j = offset * offset; j < n; j += offset) {
					isPrime[j] = false;
				}
			}
			offset++;
		}
		int offset = 2;
		while (offset < n) {
			if (isPrime[offset]) {
				count++;
			}
			offset++;
		}
		return count;
	}
}
