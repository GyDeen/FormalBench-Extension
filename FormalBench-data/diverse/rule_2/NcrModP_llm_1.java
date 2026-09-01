import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class NcrModP {

	public static int ncrModP(int n, int r, int p) {
		if (n < r) {
			return 0;
		}

		int ncr = 1;
		int c = 1;
		while (c <= r) {
			ncr = (ncr * (n - c + 1)) / c;
			c++;
		}

		return ncr % p;
	}
}
