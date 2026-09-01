import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class BinomialProbability {

	public static Double binomialProbability(int n, int k, Double p) {
		double numerator = 1.0;
		double denominator = 1.0;

		for (int i = k; i > 0; i--) {
			int tmp = n - i;
			numerator *= (tmp + 1);
			denominator *= i;
		}

		int j = n - k;
		return (numerator * Math.pow(p, k) * Math.pow(1 - p, j)) / denominator;
	}
}
