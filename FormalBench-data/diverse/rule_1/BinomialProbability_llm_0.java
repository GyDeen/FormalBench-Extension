import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class BinomialProbability {

	public static Double binomialProbability(int n, int m, Double beta) {
		double b = 1.0;
		double denomin = 1.0;

		for (int j = m; j > 0; j--) {
			b *= (n - j + 1);
			denomin *= j;
		}

		return (b * Math.pow(beta, m)
				* Math.pow(1 - beta, n - m)) / denomin;
	}
}
