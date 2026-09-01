import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class BinomialCoeff {

	public static int binomialCoeff(int n, int k) {
		if (n - k < k)
			k = n - k;
		int c = 1;
		for (int i = 1; k >= i; i++) {
			c = c * (n - i + 1) / i;
		}
		return c;
	}
}
