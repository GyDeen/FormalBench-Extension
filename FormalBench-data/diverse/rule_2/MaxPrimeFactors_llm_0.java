import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MaxPrimeFactors {

	public static int maxPrimeFactors(int n) {
		int max = 0;
		int min = 2;
		while (min <= n) {
			while (n % min == 0) {
				max = min;
				n /= min;
			}
			min++;
		}
		return max;
	}
}
