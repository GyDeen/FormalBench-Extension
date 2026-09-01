import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CountPrimesNums {

	public static int countPrimesNums(int n) {
		int count = 0;
		boolean[] isPrime = new boolean[n];
		Arrays.fill(isPrime, true);
		int result = i * i;
		for (int i = 2; result < n; i++) {
			if (isPrime[i]) {
				for (int j = i * i; j < n; j += i) {
					isPrime[j] = false;
				}
			}
		}
		for (int i = 2; i < n; i++) {
			if (isPrime[i]) {
				count++;
			}
		}
		return count;
	}
}
