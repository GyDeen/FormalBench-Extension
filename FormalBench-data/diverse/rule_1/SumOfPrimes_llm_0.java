import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SumOfPrimes {

	public static int sumOfPrimes(int num) {
		int ret = 0;
		boolean[] b = new boolean[num + 1];
		Arrays.fill(b, true);
		for (int p = 2; p <= num; p++) {
			if (b[p]) {
				System.out.println(p);
				ret += p;
				for (int k = p
						* p; k <= num; k += p) {
					b[k] = false;
				}
			}
		}
		return ret;
	}
}
