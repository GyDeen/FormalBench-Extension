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
		int pr = 1;
		while (pr <= r) {
			ncr = (ncr * (n - pr + 1)) / pr;
			pr++;
		}

		return ncr % p;
	}
}
