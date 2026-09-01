import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CountPrimesNums {

	public static int countPrimesNums(int n) {
		int count = 0;
		boolean[] isPrime = new boolean[n];
		Arrays.fill(isPrime, true);
		for (int i = 2; n > i * i; i++) {
			if (isPrime[i]) {
				for (int j = i * i; n > j; j += i) {
					isPrime[j] = false;
				}
			}
		}
		for (int i = 2; n > i; i++) {
			if (isPrime[i]) {
				count++;
			}
		}
		return count;
	}
}
