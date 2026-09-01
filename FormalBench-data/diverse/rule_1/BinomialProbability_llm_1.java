import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class BinomialProbability {

	public static Double binomialProbability(int n, int m, Double d) {
		double b = 1.0;
		double denomin = 1.0;

		for (int j = m; j > 0; j--) {
			b *= (n - j + 1);
			denomin *= j;
		}

		return (b * Math.pow(d, m)
				* Math.pow(1 - d, n - m)) / denomin;
	}
}
