import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class BinomialCoeff {

	public static int binomialCoeff(int n, int k) {
		if (k > n - k)
			k = n - k;
		int c = 1;
		int b = 1;
		while (b <= k) {
			c = c * (n - b + 1) / b;
			b++;
		}
		return c;
	}
}
