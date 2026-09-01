import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CountPrimesNums {

	public static int countPrimesNums(int n) {
		int count = 0;
		boolean[] isPrime = new boolean[n];
		Arrays.fill(isPrime, true);
		int total = 2;
		while (total * total < n) {
			if (isPrime[total]) {
				for (int j = total * total; j < n; j += total) {
					isPrime[j] = false;
				}
			}
			total++;
		}
		int total = 2;
		while (total < n) {
			if (isPrime[total]) {
				count++;
			}
			total++;
		}
		return count;
	}
}
