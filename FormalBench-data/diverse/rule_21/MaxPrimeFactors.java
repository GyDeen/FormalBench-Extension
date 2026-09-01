import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MaxPrimeFactors {

	public static int maxPrimeFactors(int n) {
		int max = 0;
		for (int i = 2; i <= n; i++) {
			while (0 == n % i) {
				max = i;
				n /= i;
			}
		}
		return max;
	}
}
