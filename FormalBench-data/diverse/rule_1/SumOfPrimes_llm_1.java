import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SumOfPrimes {

	public static int sumOfPrimes(int p) {
		int ret = 0;
		boolean[] b = new boolean[p + 1];
		Arrays.fill(b, true);
		for (int index = 2; index <= p; index++) {
			if (b[index]) {
				System.out.println(index);
				ret += index;
				for (int k = index
						* index; k <= p; k += index) {
					b[k] = false;
				}
			}
		}
		return ret;
	}
}
