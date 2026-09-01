import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class BinomialProbability {

	public static Double binomialProbability(int n, int k, Double p) {
		double numerator = 1.0;
		double denominator = 1.0;

		for (int i = k; i > 0; i--) {
			numerator = numerator * ((n - i + 1));
			denominator = denominator * i;
		}

		return (numerator * Math.pow(p, k) * Math.pow(1 - p, n - k)) / denominator;
	}
}
