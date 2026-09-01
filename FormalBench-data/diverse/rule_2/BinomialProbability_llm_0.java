import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class BinomialProbability {

	public static Double binomialProbability(int n, int k, Double p) {
		double numerator = 1.0;
		double denominator = 1.0;

		int r = k;
		while (r > 0) {
			numerator *= (n - r + 1);
			denominator *= r;
			r--;
		}

		return (numerator * Math.pow(p, k) * Math.pow(1 - p, n - k)) / denominator;
	}
}
