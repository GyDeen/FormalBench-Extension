import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class BinomialProbability {

	public static Double binomialProbability(int n, int k, Double p) {
		double numerator = 1.0;
		double denominator = 1.0;

		for (int i = k; i > 0; i--) {
			int j = n - i;
			numerator *= (j + 1);
			denominator *= i;
		}

		int end = n - k;
		return (numerator * Math.pow(p, k) * Math.pow(1 - p, end)) / denominator;
	}
}
