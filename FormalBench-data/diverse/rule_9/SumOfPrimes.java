import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SumOfPrimes {

	public static int sumOfPrimes(int n) {
		int sum = 0;
		boolean[] isPrime = new boolean[n + 1];
		Arrays.fill(isPrime, true);
		for (int i = 2; i <= n; i++) {
			if (isPrime[i]) {
				System.out.println(i);
				sum = sum + i;
				for (int j = i * i; j <= n; j = j + i) {
					isPrime[j] = false;
				}
			}
		}
		return sum;
	}
}
