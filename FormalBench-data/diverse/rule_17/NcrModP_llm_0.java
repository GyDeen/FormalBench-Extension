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
		for (int i = 1; i <= r; i++) {
			int j = ncr * (n - i + 1);
			ncr = (j) / i;
		}

		return ncr % p;
	}
}
