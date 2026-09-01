import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class BinomialProbability {

	public static Double binomialProbability(int n, int k, Double p) {
		double numerator = 1.0;
		double denominator = 1.0;

		int base = k;
		while (base > 0) {
			numerator *= (n - base + 1);
			denominator *= base;
			base--;
		}

		return (numerator * Math.pow(p, k) * Math.pow(1 - p, n - k)) / denominator;
	}
}
