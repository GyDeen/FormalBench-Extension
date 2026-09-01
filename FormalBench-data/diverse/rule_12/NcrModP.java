import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class NcrModP {

	public static int ncrModP(int n, int r, int p) {
		int ncr = 1;

		if (n < r) {
			return 0;
		}
		for (int i = 1; i <= r; i++) {
			ncr = (ncr * (n - i + 1)) / i;
		}

		return ncr % p;
	}
}
