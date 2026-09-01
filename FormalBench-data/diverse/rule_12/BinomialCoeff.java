import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class BinomialCoeff {

	public static int binomialCoeff(int n, int k) {
		int c = 1;
		if (k > n - k)
			k = n - k;
		for (int i = 1; i <= k; i++) {
			c = c * (n - i + 1) / i;
		}
		return c;
	}
}
