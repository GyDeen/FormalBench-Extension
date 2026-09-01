import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class JacobsthalLucas {

	public static int jacobsthalLucas(int c) {
		int N[] = new int[c + 1];
		N[0] = 2;
		N[1] = 1;
		for (int p = 2; p <= c; p++) {
			N[p] = N[p - 1]
					+ 2 * N[p - 2];
		}
		return N[c];
	}
}
