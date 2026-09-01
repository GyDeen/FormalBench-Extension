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
		int i = 1;
		while (i <= r) {
			ncr = (ncr * (n - i + 1)) / i;
			i++;
		}

		return ncr % p;
	}
}
