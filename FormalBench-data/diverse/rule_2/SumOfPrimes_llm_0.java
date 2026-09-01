import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SumOfPrimes {

	public static int sumOfPrimes(int n) {
		int sum = 0;
		boolean[] isPrime = new boolean[n + 1];
		Arrays.fill(isPrime, true);
		int count = 2;
		while (count <= n) {
			if (isPrime[count]) {
				System.out.println(count);
				sum += count;
				for (int j = count * count; j <= n; j += count) {
					isPrime[j] = false;
				}
			}
			count++;
		}
		return sum;
	}
}
