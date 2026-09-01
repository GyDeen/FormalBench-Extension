import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class BinomialCoeff {

	public static int binomialCoeff(int n, int k) {
		if (k > n - k)
			k = n - k;
		int c = 1;
		int r = 1;
		while (r <= k) {
			c = c * (n - r + 1) / r;
			r++;
		}
		return c;
	}
}
