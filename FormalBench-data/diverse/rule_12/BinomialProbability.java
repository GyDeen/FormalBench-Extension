import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class BinomialProbability {

	public static Double binomialProbability(int n, int k, Double p) {
		double denominator = 1.0;
		double numerator = 1.0;

		for (int i = k; i > 0; i--) {
			numerator *= (n - i + 1);
			denominator *= i;
		}

		return (numerator * Math.pow(p, k) * Math.pow(1 - p, n - k)) / denominator;
	}
}
