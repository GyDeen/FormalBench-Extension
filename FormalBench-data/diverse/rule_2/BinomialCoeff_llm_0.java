import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class BinomialCoeff {

	public static int binomialCoeff(int n, int k) {
		if (k > n - k)
			k = n - k;
		int c = 1;
		int d = 1;
		while (d <= k) {
			c = c * (n - d + 1) / d;
			d++;
		}
		return c;
	}
}
