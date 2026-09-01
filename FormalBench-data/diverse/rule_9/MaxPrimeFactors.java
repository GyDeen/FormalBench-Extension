import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MaxPrimeFactors {

	public static int maxPrimeFactors(int n) {
		int max = 0;
		for (int i = 2; i <= n; i++) {
			while (n % i == 0) {
				max = i;
				n = n / i;
			}
		}
		return max;
	}
}
