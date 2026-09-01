import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CountPrimesNums {

	public static int countPrimesNums(int num) {
		int ret = 0;
		boolean[] isPrime = new boolean[num];
		Arrays.fill(isPrime, true);
		for (int index = 2; index * index < num; index++) {
			if (isPrime[index]) {
				for (int k = index
						* index; k < num; k += index) {
					isPrime[k] = false;
				}
			}
		}
		for (int index = 2; index < num; index++) {
			if (isPrime[index]) {
				ret++;
			}
		}
		return ret;
	}
}
