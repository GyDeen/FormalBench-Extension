import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MaxPrimeFactors {

	public static int maxPrimeFactors(int n) {
		int max = 0;
		int base = 2;
		while (base <= n) {
			while (n % base == 0) {
				max = base;
				n /= base;
			}
			base++;
		}
		return max;
	}
}
