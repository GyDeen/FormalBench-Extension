import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MaxPrimeFactors {

	public static int maxPrimeFactors(int n) {
		int max = 0;
		int sum = 2;
		while (sum <= n) {
			while (n % sum == 0) {
				max = sum;
				n /= sum;
			}
			sum++;
		}
		return max;
	}
}
