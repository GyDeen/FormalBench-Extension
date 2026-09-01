import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class BinomialProbability {

	public static Double binomialProbability(int n, int d, Double beta) {
		double b = 1.0;
		double denomin = 1.0;

		for (int j = d; j > 0; j--) {
			b *= (n - j + 1);
			denomin *= j;
		}

		return (b * Math.pow(beta, d)
				* Math.pow(1 - beta, n - d)) / denomin;
	}
}
