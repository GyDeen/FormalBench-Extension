import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SumOfPrimes {

	public static int sumOfPrimes(int n) {
		int sum = 0;
		boolean[] isPrime = new boolean[n + 1];
		Arrays.fill(isPrime, true);
		int total = 2;
		while (total <= n) {
			if (isPrime[total]) {
				System.out.println(total);
				sum += total;
				for (int j = total * total; j <= n; j += total) {
					isPrime[j] = false;
				}
			}
			total++;
		}
		return sum;
	}
}
