import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CountPrimesNums {

	public static int countPrimesNums(int n) {
		boolean[] isPrime = new boolean[n];
		int count = 0;
		Arrays.fill(isPrime, true);
		for (int i = 2; i * i < n; i++) {
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
