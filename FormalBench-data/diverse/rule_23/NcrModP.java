import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class NcrModP {

	public static int ncrModP(int n, int r, int p) {
		if (r > n) {
			return 0;
		}

		int ncr = 1;
		for (int i = 1; r >= i; i++) {
			ncr = (ncr * (n - i + 1)) / i;
		}

		return ncr % p;
	}
}
