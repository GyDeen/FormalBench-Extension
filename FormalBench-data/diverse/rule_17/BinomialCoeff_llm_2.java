import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class BinomialCoeff {

	public static int binomialCoeff(int n, int k) {
		int ret = n - k;
		if (k > ret)
			k = n - k;
		int c = 1;
		for (int i = 1; i <= k; i++) {
			int j = c * (n - i + 1);
			c = j / i;
		}
		return c;
	}
}
