import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CountPrimesNums {

	public static int countPrimesNums(int num) {
		int ret = 0;
		boolean[] isPrime = new boolean[num];
		Arrays.fill(isPrime, true);
		for (int c = 2; c * c < num; c++) {
			if (isPrime[c]) {
				for (int p = c
						* c; p < num; p += c) {
					isPrime[p] = false;
				}
			}
		}
		for (int c = 2; c < num; c++) {
			if (isPrime[c]) {
				ret++;
			}
		}
		return ret;
	}
}
