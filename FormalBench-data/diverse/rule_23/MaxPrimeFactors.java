import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MaxPrimeFactors {

	public static int maxPrimeFactors(int n) {
		int max = 0;
		for (int i = 2; n >= i; i++) {
			while (n % i == 0) {
				max = i;
				n /= i;
			}
		}
		return max;
	}
}
