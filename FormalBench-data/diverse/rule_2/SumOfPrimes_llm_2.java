import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SumOfPrimes {

	public static int sumOfPrimes(int n) {
		int sum = 0;
		boolean[] isPrime = new boolean[n + 1];
		Arrays.fill(isPrime, true);
		int max = 2;
		while (max <= n) {
			if (isPrime[max]) {
				System.out.println(max);
				sum += max;
				for (int j = max * max; j <= n; j += max) {
					isPrime[j] = false;
				}
			}
			max++;
		}
		return sum;
	}
}
